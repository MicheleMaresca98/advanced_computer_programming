package agenzia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.IAgenzia;

public class AgenziaThread extends Thread {
	
	private Socket socket;
	private IAgenzia agenzia;
	
	public AgenziaThread(Socket socket, IAgenzia agenzia) {
		this.socket = socket;
		this.agenzia = agenzia;
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			String method = in.readUTF();
			if(method.equals("acquista")) {
				int quantita = in.readInt();
				agenzia.acquista(quantita);
				System.out.println("[AgenziaThread]: acquista " + quantita + " biglietti");
				out.writeUTF("ack");
			}else if(method.equals("vendi")) {
				int quantita = in.readInt();
				agenzia.vendi(quantita);
				System.out.println("[AgenziaThread]: vende " + quantita + " biglietti");
				out.writeUTF("ack");
			}else {
				System.out.println("[AgenziaThread]: invalid method");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
