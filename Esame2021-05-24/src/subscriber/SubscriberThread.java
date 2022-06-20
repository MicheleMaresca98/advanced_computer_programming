package subscriber;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import interfaces.ISubscriber;

public class SubscriberThread extends Thread{
	
	private Socket socket;
	private ISubscriber subscriber;
	
	public SubscriberThread(Socket socket, ISubscriber subscriber) {
		this.socket = socket;
		this.subscriber = subscriber;
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			String method = in.readUTF();
			if(method.equals("notifyAlert")) {
				int criticality = in.readInt();
				out.writeUTF("ack");
				subscriber.notifyAlert(criticality);
				System.out.println("[SubscriberThread]: notificato di alert con criticality " + criticality);
				in.close();
				out.close();
				socket.close();
			}else {
				System.out.println("[Subscriber]: invalid method");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
