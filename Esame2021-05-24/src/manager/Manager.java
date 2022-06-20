package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Manager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IManager manager = new ManagerImpl();
			rmiRegistry.rebind("manager", manager);
			System.out.println("[Manager]: avviato");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
