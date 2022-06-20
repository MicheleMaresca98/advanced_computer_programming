package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILogService extends Remote{
	
	public boolean stampa(int temperatura) throws RemoteException;

}
