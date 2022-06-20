package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IClient;
import interfaces.IMagazzino;

public class ClientImpl extends UnicastRemoteObject implements IClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2044613806435596169L;
	
	private IMagazzino magazzino;

	protected ClientImpl(IMagazzino magazzino) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.magazzino = magazzino;
		
	}

	@Override
	public void nuovoArticolo() throws RemoteException {
		// TODO Auto-generated method stub
		int id = magazzino.preleva();
		System.out.println("[CllientImpl]: ha prelevato il nuovo articolo con id " + id);
		
	}

}
