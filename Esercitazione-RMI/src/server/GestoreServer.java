package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestoreSportello;

public class GestoreServer {
	
	public static void main (String[] args) {
		
		try {
			
			IGestoreSportello gestore = new GestoreSportelloImpl();
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind("gestore", gestore);
			
			System.out.println("[GestoreServer]: Gestore sportelli avviato");
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
