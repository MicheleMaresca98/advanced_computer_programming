package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Generator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nthreads = 3;
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
		
			IManager manager = (IManager) rmiRegistry.lookup("manager");
			Thread[] threads = new Thread[nthreads];
			for(int i=0; i<nthreads; i++) {
				threads[i] = new ComponentT(manager);
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
