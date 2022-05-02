package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import service.ICounter;

public abstract class CounterSkeleton implements ICounter {
	
	public void runSkeleton() {
		try {
			DatagramSocket socket = new DatagramSocket(9000);
			
			System.out.println("Server in ascolto sulla porta 9000");
			while(true) {
				byte[] buffer = new byte[65508];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socket.receive(request);
				
				Thread worker = new SkeletonThread(socket, request, this);
				worker.start();
			}
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
