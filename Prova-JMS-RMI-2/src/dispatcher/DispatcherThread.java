package dispatcher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import interfaces.IPrinter;


public class DispatcherThread extends Thread{
	
	private MapMessage message;
	
	public DispatcherThread(MapMessage message) {
		this.message = message;
	}
	
	public void run() {
		try {
			String nomeDocumento = message.getString("nomeDocumento");
			String nomePrinter = message.getString("nomePrinter");
			System.out.println("[DispatcherThread]: ha ricevuto in stampa il documento " + nomeDocumento);
			Registry rmiRegistry = (Registry) LocateRegistry.getRegistry();
			IPrinter printer = (IPrinter) rmiRegistry.lookup(nomePrinter);
			printer.printDoc(nomeDocumento);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
