package produttore;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IMagazzino;

public class Produttore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nthreads = 3;
		Thread[] threads = new Thread[nthreads];
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
		
			IMagazzino magazzino = (IMagazzino) rmiRegistry.lookup("magazzino");
			
			for(int i=0; i<nthreads; i++) {
				threads[i] = new ProduttoreThread(magazzino);
				threads[i].start();
			}
			for(int i=0; i<nthreads; i++) {
				threads[i].join();
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
