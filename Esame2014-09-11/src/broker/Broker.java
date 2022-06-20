package broker;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBroker;

public class Broker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IBroker broker = new BrokerImpl();
			rmiRegistry.rebind("broker", broker);
			System.out.println("[Broker]: avviato");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
