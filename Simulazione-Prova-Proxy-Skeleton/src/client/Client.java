package client;

import interfaces.IGestore;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nthreads = 10;
		int nrequests = 5;
		Thread[] threads = new Thread[nthreads];
		
		int port = 9000;
		IGestore gestore = new GestoreProxy(port);
		
		for(int i=0; i<nthreads; i++) {
			threads[i] = new ClientThread(gestore, nrequests);
			threads[i].start();
		}
		for(int i=0; i<nthreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
