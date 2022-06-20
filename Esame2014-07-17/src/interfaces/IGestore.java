package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGestore extends Remote {
	
	public void sottoscrivi(int portDeposito) throws RemoteException;
	public boolean richiestaDeposito(int id_articolo) throws RemoteException;

}
