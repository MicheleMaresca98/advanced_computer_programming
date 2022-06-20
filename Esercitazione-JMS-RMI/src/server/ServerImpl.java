package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import coda.CodaCircolare;
import coda.CodaWrapperLock;
import interfaces.IServer;

public class ServerImpl extends UnicastRemoteObject implements IServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2454604887902752514L;
	
	private CodaWrapperLock coda;

	protected ServerImpl(int size) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		coda = new CodaWrapperLock(new CodaCircolare(size));
	}

	@Override
	public void deposita(int value) throws RemoteException {
		// TODO Auto-generated method stub
		coda.deposita(value);
	}

	@Override
	public int preleva() throws RemoteException {
		// TODO Auto-generated method stub
		return coda.preleva();
	}

}
