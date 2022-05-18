package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;

public class Client {
	
	public static void main (String[] args) {
		
		try {
			int T = 10;
			int R = 10;
		
			Thread[] threads = new Thread[T];
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			IGestoreSportello gestore = (IGestoreSportello)rmiRegistry.lookup("gestore");
			
			for(int i=0; i<T; i++) {
				threads[i] = new ClientThread(R, gestore);
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
		
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				
		
	}

}
