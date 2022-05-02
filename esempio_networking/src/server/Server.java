package server;

import java.io.IOException;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8050);
			System.out.println("[Server]: in attesa su porta 8050.");
			
			while(true) {
				Socket s = server.accept();
				
				System.out.println("[Server]: nuovo client, avvio del thread Worker.");
				WorkerServer w = new WorkerServer(s);
				w.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
