package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalcolatrice extends Remote{
	
	public int add(int i1, int i2) throws RemoteException;
	public int mul(int i1, int i2) throws RemoteException;

}
