package servernews;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IServerNews;

public class ServerNewsImpl extends UnicastRemoteObject implements IServerNews {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3325056337727401757L;

	protected ServerNewsImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void publ_news(String notizia, String tipo) throws RemoteException {
		// TODO Auto-generated method stub
		Thread thread = new ServerNewsThread(notizia, tipo);
		System.out.println("[ServerNewsImpl]: ricevuta notizia " + notizia + " di tipo " + tipo + " avviato thread");
		thread.start();
	}

}
