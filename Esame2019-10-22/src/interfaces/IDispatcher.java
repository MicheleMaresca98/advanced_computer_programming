package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Reading;

public interface IDispatcher extends Remote{
	
	public void setReading(Reading reading) throws RemoteException;
	public void attach(Observer observer, String string) throws RemoteException;
	public Reading getReading() throws RemoteException;
	
	

}
