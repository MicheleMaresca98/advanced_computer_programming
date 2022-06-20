package server;

import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IDispatcher;
import interfaces.Observer;

public class ObserverImpl extends UnicastRemoteObject implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IDispatcher dispatcher;
	private PrintWriter pw;

	protected ObserverImpl(IDispatcher dispatcher, PrintWriter pw) throws RemoteException {
		this.dispatcher = dispatcher;
		this.pw = pw;
	}

	@Override
	public void notifyReading() throws RemoteException {
		// TODO Auto-generated method stub
		Reading reading = dispatcher.getReading();
		String tipo = reading.getTipo();
		int valore = reading.getValore();
		System.out.println("[ObserverImpl] ha letto Tipo: "+tipo+" Valore: "+valore);
		pw.println("Lettura: Tipo: "+tipo+" Valore: "+valore);
		pw.flush();
		
	}

}
