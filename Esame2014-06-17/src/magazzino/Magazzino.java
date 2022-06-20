package magazzino;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IMagazzino;

public class Magazzino {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 10; // dimensione coda circolare magazzino
		try {
			
			IMagazzino magazzino = new MagazzinoImpl(size);
		
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind("magazzino", magazzino);
			System.out.println("[Magazzino]: avviato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
