package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class Generator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int nthreads = 3;
			int nrequests = 3;
			Thread[] threads = new Thread[nthreads];
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IDispatcher dispatcher = (IDispatcher)rmiRegistry.lookup("dispatcher");
			
			
			for(int i=0;i<nthreads;i++) {
				threads[i] = new GeneratorThread(dispatcher, nrequests);
				threads[i].start();
			}
			
			for(int i=0;i<nthreads;i++) {
				threads[i].join();
			}
		
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
