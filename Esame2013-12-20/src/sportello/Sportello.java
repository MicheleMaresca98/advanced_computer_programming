package sportello;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestore;
import interfaces.ISportello;

public class Sportello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathfile = args[0];
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestore gestore = (IGestore) rmiRegistry.lookup("gestore");
			ISportello sportello = new SportelloImpl(gestore, pathfile);
			gestore.sottoscrivi(sportello);
			
			System.out.println("[Sportello]: avviato e sottoscritto a gestore");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
