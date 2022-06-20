package subscriber;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.ISubscriber;

public class SubscriberSkeleton implements ISubscriber {
	
	private int port;
	private ISubscriber subscriber;
	
	public SubscriberSkeleton(int port, ISubscriber subscriber) {
		this.port = port;
		this.subscriber = subscriber;
	}
	
	public void runSkeleton() {
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("[SubscriberSkeleton]: in ascolto su porta " + port);
			while(true) {
				Socket socket = server.accept();
				Thread thread = new SubscriberThread(socket, this);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void notifyAlert(int criticality) {
		// TODO Auto-generated method stub
		subscriber.notifyAlert(criticality);
	}

}
