package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import dispatcher.IDispatcher;

public class DispatcherThread extends Thread{
	
	private Socket socket;
	private IDispatcher dispatcher;
	
	public DispatcherThread(Socket socket, IDispatcher dispatcher) {
		this.socket = socket;
		this.dispatcher = dispatcher;
	}
	
	public void run() {
		try {
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			
			String method = inStream.readUTF();
			System.out.println("[DispatcherThread]: il server ha ricevuto il metodo " + method);
			
			if (method.equals("sendCmd")) {
				int command = inStream.readInt();
				System.out.println("[DispatcherThread]: il command ricevuto è " + command);
				dispatcher.sendCmd(command);
				outStream.writeUTF("ack");
				
			}else if(method.equals("getCmd")) {
				int x = dispatcher.getCmd();
				System.out.println("[DispatcherThread]: il command scritto su file è " + x);
				outStream.writeInt(x);
				
			}else {
				System.out.println("Invalid method");
			}
			
			inStream.close();
			outStream.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
