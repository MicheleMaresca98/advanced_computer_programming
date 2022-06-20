package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ICalcolatrice;

public class CalcolatriceImpl extends UnicastRemoteObject implements ICalcolatrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3961488174526443673L;
	
	protected CalcolatriceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(int i1, int i2) throws RemoteException {
		// TODO Auto-generated method stub
		
		return (i1 + i2);
	}

	@Override
	public int mul(int i1, int i2) throws RemoteException {
		// TODO Auto-generated method stub
		return (i1 * i2);
	}

}
