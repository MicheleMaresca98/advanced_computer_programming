package newsagency;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IServerNews;


public class NewsAgency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String tipo = args[0]; // sport o finance
		int n_notizie = 5;
		
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			IServerNews serverNews = (IServerNews) rmiRegistry.lookup("serverNews");
			
			for(int i=0; i<n_notizie; i++) {
				String notizia = new String("Notizia" + (int)(Math.random()*101));
				serverNews.publ_news(notizia, tipo);
				System.out.println("[NewsAgency]: ha pubblicato la notizia " + notizia + " di tipo " + tipo);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
