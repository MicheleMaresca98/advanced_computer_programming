package sportello;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.ISportello;

public abstract class SportelloSkeleton implements ISportello {

	private int port;
	
	public SportelloSkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		try {
			ServerSocket server = new ServerSocket(port);
		
			System.out.println("[SportelloSkeleton]: avviato");
			while(true) {
				Socket socket = server.accept();
				Thread thread = new SportelloThread(socket, this);
				thread.start();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
