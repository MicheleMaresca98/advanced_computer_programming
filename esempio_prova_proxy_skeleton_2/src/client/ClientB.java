package client;

public class ClientB {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nthreadsB = 5;
		Thread[] threadsB = new Thread[nthreadsB];
		
		ClientProxy clientProxy = new ClientProxy();
		
		for (int i = 0; i < nthreadsB; i++) {
			threadsB[i] = new ThreadClientB(clientProxy);
			threadsB[i].start();
		}
		
		
		for (int i = 0; i < nthreadsB; i++) {
			try {
				threadsB[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
