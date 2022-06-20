package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerNews extends Remote {
	
	public void publ_news(String notizia, String tipo) throws RemoteException;

}
