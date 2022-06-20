package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMagazzino extends Remote {
	
	public void sottoscrivi(IClient client) throws RemoteException;
	public void deposita(int id) throws RemoteException;
	public int preleva() throws RemoteException;

}
