package deposito;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IGestore;

public class Deposito {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int port = 9000;
		//String pathfile = "./articoli.txt";
		int port = Integer.valueOf(args[0]).intValue();
		String pathfile = args[1];
		
		DepositoImpl depositoImpl = new DepositoImpl(pathfile);
		DepositoSkeleton depositoSkeleton = new DepositoSkeleton(port, depositoImpl);
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IGestore gestore = (IGestore) rmiRegistry.lookup("gestore");
			gestore.sottoscrivi(port);
			System.out.println("[Deposito]: avviato su porta " + port + " e sottoscritto a gestore");
			depositoSkeleton.runSkeleton();
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
