package printer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IPrinter;

public class Printer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nomePrinter = args[0];
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IPrinter printer = new PrinterImpl();
			rmiRegistry.rebind(nomePrinter, printer);
			System.out.println("[Printer]: avviata");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
