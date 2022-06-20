package sportello;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.ISportello;

public class SportelloThread extends Thread {
	
	private Socket socket;
	private ISportello sportello;
	
	public SportelloThread(Socket socket, ISportello sportello) {
		this.socket = socket;
		this.sportello = sportello;
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			String method = in.readUTF();
			if(method.equals("serviRichiesta")) {
				int id_cliente = in.readInt();
				boolean esito = sportello.serviRichiesta(id_cliente);
				out.writeBoolean(esito);
				System.out.println("[SportelloThread]: ha servito richiesta di id_cliente " + id_cliente + 
						" con esito " + esito);

			}else {
				System.out.println("[SportelloThread]: invalid method");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
