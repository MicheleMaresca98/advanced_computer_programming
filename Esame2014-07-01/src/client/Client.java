package client;

import interfaces.IBroker;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int T = 10; // numero threads
		int R = 5; // numero richieste per ciascun thread
		Thread[] threads = new Thread[T];
		
		int port = 9000;
		
		IBroker broker = new BrokerProxy(port);
		
		for(int i=0; i<T; i++) {
			threads[i] = new ClientThread(broker, R);
			threads[i].start();
		}
		
		for(int i=0; i<T; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
