package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class DispatcherServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 
		try {
			IDispatcher dispatcher = new DispatcherImpl();
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind("dispatcher", dispatcher);
			System.out.println("[DispatcherServer] dispatcher server avviato.");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
