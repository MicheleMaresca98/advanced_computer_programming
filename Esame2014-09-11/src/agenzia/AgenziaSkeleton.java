package agenzia;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.IAgenzia;

public abstract class AgenziaSkeleton implements IAgenzia{
	
	private int port;
	
	public AgenziaSkeleton(int port) {
		this.port = port;
	}

	public void runSkeleton() {
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("[AgenziaSkeleton]: avviata alla porta " + port);
			while(true) {
				Socket socket = server.accept();
				Thread thread = new AgenziaThread(socket, this);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
