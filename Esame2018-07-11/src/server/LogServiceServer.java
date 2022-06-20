package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.ILogService;

public class LogServiceServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			ILogService logService = new LogServiceImpl();
			rmiRegistry.rebind("logService", logService);
			
			System.out.println("[LogServiceServer] avviato");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
