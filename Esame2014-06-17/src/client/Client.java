package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IClient;
import interfaces.IMagazzino;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IMagazzino magazzino = (IMagazzino) rmiRegistry.lookup("magazzino");
			IClient client = new ClientImpl(magazzino);
			magazzino.sottoscrivi(client);
			
			System.out.println("[Client]: avviato e sottoscritto al magazzino");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
