package dispatcher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import interfaces.IPrinter;

public class DispatcherThread extends Thread {
	
	private MapMessage message;
	
	public DispatcherThread(MapMessage message) {
		this.message = message;
	}
	
	public void run() {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IPrinter printer = (IPrinter) rmiRegistry.lookup(message.getString("nomePrinter"));
			System.out.println("[DispathcerThread]: ha ricevuto nomeDocumento " + message.getString("nomeDocumento"));
			printer.printDoc(message.getString("nomeDocumento"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
