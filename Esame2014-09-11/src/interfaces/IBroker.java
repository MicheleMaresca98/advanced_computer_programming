package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBroker extends Remote {
	
	public void sottoscrivi(int port) throws RemoteException;
	public void sottoponi(int tipoOperazione, int quantita) throws RemoteException;
	// tipoOperazione 1=acquista, 2=vendi


}
