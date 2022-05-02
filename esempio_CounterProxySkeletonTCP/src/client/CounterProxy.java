package client;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import service.ICounter;

public class CounterProxy implements ICounter {
	
	private InetAddress host;
	private int port;
	
	public CounterProxy(InetAddress host, int port) {
		this.host=host;
		this.port=port;
	}

	@Override
	public void inc() {
		// TODO Auto-generated method stub
		try {
			
			Socket socket = new Socket(host, port);
			DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream streamIn = new DataInputStream(socket.getInputStream());
			
			streamOut.writeUTF("inc");
			
			String responseString = streamIn.readUTF();
			System.out.println("[CounterProxy]: inc ha ricevuto la seguente risposta: "+ responseString);
			
			socket.close();

			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	@Override
	public void sum(int value) {
		// TODO Auto-generated method stub
		try {
			
			Socket socket = new Socket(host, port);
			DataOutputStream streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			DataInputStream streamIn = new DataInputStream(socket.getInputStream());
			
			streamOut.writeUTF("sum");
			streamOut.writeInt(value);
			streamOut.flush();
			
			String responseString = streamIn.readUTF();
			System.out.println("[CounterProxy]: sum ha ricevuto la seguente risposta: "+ responseString);
			
			socket.close();

			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int get() {
		// TODO Auto-generated method stub
		int x=0;
		try {
			
			Socket socket = new Socket(host, port);
			DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream streamIn = new DataInputStream(socket.getInputStream());
			
			streamOut.writeUTF("get");
			
			x = streamIn.readInt();
			System.out.println("[CounterProxy]: get ha ricevuto la seguente risposta: "+ x);
			
			socket.close();

			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return x;
		
	}

	@Override
	public void square() {
		// TODO Auto-generated method stub
		try {
			
			Socket socket = new Socket(host, port);
			DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
			DataInputStream streamIn = new DataInputStream(socket.getInputStream());
			
			streamOut.writeUTF("square");
			
			String responseString = streamIn.readUTF();
			System.out.println("[CounterProxy]: square ha ricevuto la seguente risposta: "+ responseString);
			
			socket.close();

			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
