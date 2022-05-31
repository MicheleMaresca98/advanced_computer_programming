package printer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IDispatcher;

public class PrinterServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			int port = Integer.valueOf(args[0]).intValue();
			String file = args[1];
			
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IDispatcher dispatcher = (IDispatcher) rmiRegistry.lookup("dispatcher");
			
			PrinterImpl printerImpl = new PrinterImpl(file);
			PrinterSkeleton printerSkeleton = new PrinterSkeleton(printerImpl, port);
			
			dispatcher.addPrinter("localhost", port);
			System.out.println("[PrinterServer] nuova printer avviata e aggiunta a dispatcher");
			
			printerSkeleton.runSkeleton();
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
