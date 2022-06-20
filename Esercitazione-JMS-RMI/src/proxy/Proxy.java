package proxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IServer;


public class Proxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IServer server = (IServer) rmiRegistry.lookup("server");
			Thread receiver = new Receiver(server);
			receiver.start();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
