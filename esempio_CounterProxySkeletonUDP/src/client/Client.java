package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import service.ICounter;

public class Client {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		InetAddress host = InetAddress.getLocalHost();
		
		int port = 9000;
		ICounter counter = new CounterProxy(host, port);
		
		String operation = args[0];
		
		if(operation.compareTo("inc") == 0) {
			counter.inc();
			System.out.println("[Client]: Inc effettuated");
			
		}else if(operation.compareTo("sum") == 0) {
			int value = Integer.valueOf(args[1]).intValue();
			counter.sum(value);
			System.out.println("[Client]: Sum effettuated");
			
		}else if(operation.compareTo("get") == 0) {
			System.out.println("[Client]: Counter value: "+counter.get());
			
		}else if(operation.compareTo("square") == 0) {
			counter.square();
			System.out.println("[Client]: Square effettuated");
			
		}
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
