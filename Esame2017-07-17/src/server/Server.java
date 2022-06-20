package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.ICalcolatrice;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ICalcolatrice calcolatrice = new CalcolatriceImpl();
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind("calcolatrice", calcolatrice);
			
			System.out.println("[Server] Calcolatrice avviata");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
