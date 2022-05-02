package server;

import java.io.*;
import java.net.*;

public class WorkerServer extends Thread{
	
	private Socket s;
	
	public WorkerServer (Socket skt) {
		s=skt;
	}
	
	public void run() {
		try {
			DataInputStream fromClient = new DataInputStream(s.getInputStream());
			DataOutputStream toClient = new DataOutputStream(s.getOutputStream());
			
			System.out.println("[Server-Worker]: attesa stringa dal client...");
			String st = fromClient.readUTF();
			System.out.println("[Server-Worker]: stringa ricevuta < "+st+" >. Invio risposta.");
			
			toClient.writeUTF("richiesta ricevuta");
			
			fromClient.close();
			toClient.close();
			s.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
