package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IDispatcher;
import interfaces.Observer;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Observer> observersTemperatura;
	private Vector<Observer> observerPressione;
	private Reading reading;

	protected DispatcherImpl() throws RemoteException {
		observersTemperatura = new Vector<Observer>();
		observerPressione = new Vector<Observer>();
	}

	@Override
	public synchronized void setReading(Reading reading) throws RemoteException {
		// TODO Auto-generated method stub
		this.reading = reading;
		String tipo = reading.getTipo();
		if(tipo.equals("temperatura")) {
			for(Observer o : observersTemperatura) {
				o.notifyReading();
				System.out.println("[DispatcherImpl] notificato observer temperatura");
			}
			
		}else if(tipo.equals("pressione")) {
			for(Observer o : observerPressione) {
				o.notifyReading();
				System.out.println("[DispatcherImpl] notificato observer pressione");
			}
			
		}else {
			System.out.println("[DispatcherImpl] invalid tipo in setReading");
		}
		try {
			Thread.sleep((int)(1 + Math.random()*5)*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void attach(Observer observer, String tipo) throws RemoteException {
		// TODO Auto-generated method stub
		if(tipo.equals("temperatura")) {
			observersTemperatura.add(observer);
			System.out.println("[DispatcherImpl] aggiunto observer temperatura");
		}else if(tipo.equals("pressione")) {
			observerPressione.add(observer);
			System.out.println("[DispatcherImpl] aggiunto observer pressione");
		}else {
			System.out.println("[DispatcherImpl] invalid tipo in attach");
		}
		
	}

	@Override
	public Reading getReading() throws RemoteException {
		// TODO Auto-generated method stub
		return reading;
	}

}
