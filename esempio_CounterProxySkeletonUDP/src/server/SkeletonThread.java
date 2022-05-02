package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import service.ICounter;

public class SkeletonThread extends Thread{
	
	private DatagramSocket socket;
	private DatagramPacket packet;
	private ICounter counter;
	
	public SkeletonThread(DatagramSocket socket, DatagramPacket packet, ICounter counter) {
		this.socket = socket;
		this.packet = packet;
		this.counter = counter;
	}
	
	public void run() {
	
		String message = new String(packet.getData(), 0, packet.getLength());
		StringTokenizer tokenizer = new StringTokenizer(message, "#");
		
		String method = tokenizer.nextToken();
		
		if (method.equals("inc")) {
			counter.inc();
			String responseString = "ack";
			DatagramPacket response = new DatagramPacket(responseString.getBytes(),
					responseString.getBytes().length, packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equals("sum")) {
			int value = Integer.valueOf(tokenizer.nextToken()).intValue();
			counter.sum(value);
			String responseString = "ack";
			DatagramPacket response = new DatagramPacket(responseString.getBytes(),
					responseString.getBytes().length, packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(method.equals("get")) {
			int x = counter.get();
			String responseString = Integer.toString(x);
			DatagramPacket response = new DatagramPacket(responseString.getBytes(),
					responseString.getBytes().length, packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equals("square")) {
			counter.square();
			String responseString = "ack";
			DatagramPacket response = new DatagramPacket(responseString.getBytes(),
					responseString.getBytes().length, packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("[SkeletonThread]: errore richiesta");
		}
	}
	

}
