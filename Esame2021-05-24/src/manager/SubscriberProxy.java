package manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import interfaces.ISubscriber;

public class SubscriberProxy implements ISubscriber {
	
	private int port;
	
	public SubscriberProxy(int port) {
		this.port = port;
	}

	@Override
	public void notifyAlert(int criticality) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
		
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF("notifyAlert");
			out.writeInt(criticality);
			
			String response = in.readUTF();
			System.out.println("[SubscribeRProxy]: ha inviato notifica alert con criticality " + criticality + 
					" ed ha ricevuto " + response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 

}
