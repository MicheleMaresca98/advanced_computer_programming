package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestore;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int T = 10;
		int R = 5;
		Thread[] threads = new Thread[T];
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
		
			IGestore gestore = (IGestore) rmiRegistry.lookup("gestore");
			
			for(int i=0; i<T; i++) {
				threads[i] = new ClientThread(gestore, R);
				threads[i].start();
			}
			for(int i=0; i<T; i++) {
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
