package client;

import dispatcher.IDispatcher;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IDispatcher dispatcherProxy = new DispatcherProxy();
		int nthreads = 5;
		Thread[] threads = new Thread[nthreads];
			
		for(int i = 0; i < nthreads; i++) {
			threads[i] = new ClientThread(dispatcherProxy);
			threads[i].start();
		}
		for(int i = 0; i < nthreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
