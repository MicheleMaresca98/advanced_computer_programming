package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import service.ICounter;

public class CounterSkeletonDelega implements ICounter {
	
	private ICounter counterImpl;
	
	public CounterSkeletonDelega(ICounter counterImpl){
		this.counterImpl = counterImpl;
	}
	
	public void runSkeleton() {
		try {
			ServerSocket server = new ServerSocket(9000);
				
			System.out.println("Server in ascolto sulla porta 9000");
			while(true) {
				Socket socket = server.accept();
					
				Thread worker = new SkeletonThread(socket, this);
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

	@Override
	public void inc() {
		// TODO Auto-generated method stub
		counterImpl.inc();
		
	}

	@Override
	public void sum(int value) {
		// TODO Auto-generated method stub
		counterImpl.sum(value);
		
	}

	@Override
	public int get() {
		// TODO Auto-generated method stub
		int x = counterImpl.get();
		return x;
	}

	@Override
	public void square() {
		// TODO Auto-generated method stub
		counterImpl.square();
		
	}

}
