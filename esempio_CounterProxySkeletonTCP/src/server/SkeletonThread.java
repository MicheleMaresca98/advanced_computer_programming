package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.ICounter;

public class SkeletonThread extends Thread{
	
	private Socket socket;
	private ICounter counter;
	
	public SkeletonThread(Socket socket, ICounter counter) {
		this.socket = socket;
		this.counter = counter;
	}
	
	public void run() {
		try {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		
		String method;
		
			method = inputStream.readUTF();
		
		
		if (method.equals("inc")) {
			counter.inc();
			try {
				outputStream.writeUTF("ack");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equals("sum")) {
			try {
			int value = inputStream.readInt();
			counter.sum(value);
			outputStream.writeUTF("ack");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}else if(method.equals("get")) {
			int x = counter.get();
			try {
				outputStream.writeInt(x);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equals("square")) {
			counter.square();
			try {
				outputStream.writeUTF("ack");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("[SkeletonThread]: errore richiesta");
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	

}
