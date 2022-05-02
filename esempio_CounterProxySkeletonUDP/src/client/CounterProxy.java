package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
			
			String operation = "inc#";
			
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(operation.getBytes(), operation.getBytes().length, host, port);
			socket.send(packet);
			
			byte[] buffer = new byte[65508];
			
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			socket.receive(response);
			String responseString = new String(response.getData(), 0, response.getLength());
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
			
			String operation = "sum#" + value + "#";
			
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(operation.getBytes(), operation.getBytes().length, host, port);
			socket.send(packet);
			
			byte[] buffer = new byte[65508];
			
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			socket.receive(response);
			String responseString = new String(response.getData(), 0, response.getLength());
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
					
			String operation = "get#";
					
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(operation.getBytes(), operation.getBytes().length, host, port);
			socket.send(packet);
					
			byte[] buffer = new byte[65508];
					
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			socket.receive(response);
			String responseString = new String(response.getData(), 0, response.getLength());
			x = Integer.valueOf(responseString).intValue();
			System.out.println("[CounterProxy]: get ha ricevuto la seguente risposta: "+ responseString);
					
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
					
					String operation = "square#";
					
					DatagramSocket socket = new DatagramSocket();
					DatagramPacket packet = new DatagramPacket(operation.getBytes(), operation.getBytes().length, host, port);
					socket.send(packet);
					
					byte[] buffer = new byte[65508];
					
					DatagramPacket response = new DatagramPacket(buffer, buffer.length);
					socket.receive(response);
					String responseString = new String(response.getData(), 0, response.getLength());
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
