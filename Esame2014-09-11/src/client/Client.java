package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBroker;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			int T = 5;int R = 25;
			Thread[] threads = new Thread[T];
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IBroker broker = (IBroker) rmiRegistry.lookup("broker"); 
			
			for(int i=0; i<T; i++) {
				threads[i] = new ClientThread(broker, R);
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
