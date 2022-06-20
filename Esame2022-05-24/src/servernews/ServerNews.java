package servernews;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IServerNews;

public class ServerNews {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			IServerNews serverNews = new ServerNewsImpl();
			Registry rmiRegistry = LocateRegistry.getRegistry();
			rmiRegistry.rebind("serverNews", serverNews);
			System.out.println("[ServerNews]: avviato");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
