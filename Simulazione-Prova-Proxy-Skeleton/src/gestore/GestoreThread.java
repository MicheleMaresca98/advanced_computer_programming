package gestore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IGestore;

public class GestoreThread implements Runnable {
	
	private Socket socket;
	private IGestore gestore;
	
	public GestoreThread(Socket socket, IGestore gestore) {
		this.socket = socket;
		this.gestore = gestore;
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			String method = in.readUTF();
			if(method.equals("prenota")) {
				int matricola = in.readInt();
				boolean limitazione = in.readBoolean();
				boolean esito = gestore.prenota(matricola, limitazione);
				out.writeBoolean(esito);
				//System.out.println("[GestoreThread]: ha prenotato esame con matricola " + matricola);
			}else {
				System.out.println("[GestoreThread]: invalid method");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
