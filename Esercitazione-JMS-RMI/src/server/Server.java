package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IServer;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			int size = 5; // dimensione coda magazzino
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IServer server = new ServerImpl(size);
			rmiRegistry.rebind("server", server);
			System.out.println("[Server]: avviato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
