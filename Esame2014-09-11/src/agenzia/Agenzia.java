package agenzia;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IBroker;

public class Agenzia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int B = 10; // numero iniziale di biglietti
			int port = Integer.valueOf(args[0]).intValue();
			AgenziaImpl agenzia = new AgenziaImpl(port, B);
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IBroker broker = (IBroker) rmiRegistry.lookup("broker");
			broker.sottoscrivi(port);
			System.out.println("[Agenzia]: avviata su porta " + port + " e sottoscritta al broker");
			agenzia.runSkeleton();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
