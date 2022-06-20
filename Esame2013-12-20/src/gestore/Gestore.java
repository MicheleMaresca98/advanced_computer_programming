package gestore;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestore;

public class Gestore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestore gestore = new GestoreImpl();
			rmiRegistry.rebind("gestore", gestore);
			
			System.out.println("[Gestore]: avviato");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
