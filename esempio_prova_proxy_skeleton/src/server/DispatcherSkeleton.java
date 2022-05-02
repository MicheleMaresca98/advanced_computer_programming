package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dispatcher.IDispatcher;

public abstract class DispatcherSkeleton implements IDispatcher {
	
	public void runSkeleton() {
		ServerSocket server;
		try {
			int port = 9000;
			server = new ServerSocket(port);
			System.out.println("[DispatcherSkeleton]: il server è in attesa sulla porta" + port);
			while(true) {
				Socket socket = server.accept();
				DispatcherThread worker = new DispatcherThread(socket, this);
				worker.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
