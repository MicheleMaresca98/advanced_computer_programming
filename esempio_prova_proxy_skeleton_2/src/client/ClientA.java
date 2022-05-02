package client;

public class ClientA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nthreadsA = 5;
		Thread[] threadsA = new Thread[nthreadsA];
		
		ClientProxy clientProxy = new ClientProxy();
		
		for (int i = 0; i < nthreadsA; i++) {
			threadsA[i] = new ThreadClientA(clientProxy);
			threadsA[i].start();
		}
		
		
		for (int i = 0; i < nthreadsA; i++) {
			try {
				threadsA[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
