package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import dispatcher.IDispatcher;

public class DispatcherProxy implements IDispatcher{

	@Override
	public void sendCmd(int command) {
		// TODO Auto-generated method stub
		Socket socket;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9000);
		
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			outStream.writeUTF("sendCmd");
			outStream.writeInt(command);
			String response = inStream.readUTF();
			System.out.println("Ricevuto " + response);
			
			inStream.close();
			outStream.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int getCmd() {
		// TODO Auto-generated method stub
		Socket socket;
		int response = 0;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9000);
		
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			outStream.writeUTF("getCmd");
			response = inStream.readInt();
			
			inStream.close();
			outStream.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	

}
