package subscriber;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IManager;

public class Subscriber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int componentID = Integer.valueOf(args[0]).intValue();
		int port = Integer.valueOf(args[1]).intValue();
		String pathfile = args[2];
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IManager manager = (IManager) rmiRegistry.lookup("manager");

			SubscriberImpl subscriberImpl = new SubscriberImpl(pathfile);
			SubscriberSkeleton subscriberSkeleton = new SubscriberSkeleton(port, subscriberImpl);
			manager.subscribe(componentID, port);
			System.out.println("[Subscriber]: avviato e sottoscritto a manager");
			
			subscriberSkeleton.runSkeleton();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
