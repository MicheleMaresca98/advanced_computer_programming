package gestore;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interfaces.IGestore;

public abstract class GestoreSkeleton implements IGestore {
	
	private int port;
	
	public GestoreSkeleton(int port) {
		this.port = port;
	}
	
	public void runSkeleton() {
		try {
			ExecutorService executors = Executors.newFixedThreadPool(5);
					
			ServerSocket server = new ServerSocket(port);
		
			System.out.println("[GestoreSkeleton]: avviato alla porta " + port);
			while(true) {
				Socket socket = server.accept();
				GestoreThread thread = new GestoreThread(socket, this);
				executors.execute(thread);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
