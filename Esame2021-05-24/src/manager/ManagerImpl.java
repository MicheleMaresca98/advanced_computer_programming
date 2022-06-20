package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import alertnotification.AlertNotification;
import interfaces.IManager;
import interfaces.ISubscriber;

public class ManagerImpl extends UnicastRemoteObject implements IManager {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8763710857981117738L;
	private Vector<ISubscriber> subscribers1;
	private Vector<ISubscriber> subscribers2;
	private Vector<ISubscriber> subscribers3;
	private Vector<ISubscriber> subscribers4;
	private Vector<ISubscriber> subscribers5;

	protected ManagerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		subscribers1 = new Vector<ISubscriber>();
		subscribers2 = new Vector<ISubscriber>();
		subscribers3 = new Vector<ISubscriber>();
		subscribers4 = new Vector<ISubscriber>();
		subscribers5 = new Vector<ISubscriber>();
	}
	
	@Override
	public synchronized void sendNotification(AlertNotification alertNotification) throws RemoteException {
		// TODO Auto-generated method stub
		int componentID = alertNotification.getComponentID();
		int i=0;
		if(componentID == 1) {
			for(ISubscriber subscriber : subscribers1) {
				subscriber.notifyAlert(componentID);
				System.out.println("[ManagerImpl]: notificato subscribers1["+i+"]");
				i++;
			}
			
		}else if(componentID == 2) {
			for(ISubscriber subscriber : subscribers2) {
				subscriber.notifyAlert(componentID);
				System.out.println("[ManagerImpl]: notificato subscribers2["+i+"]");
				i++;
			}
		}else if(componentID == 3) {
			for(ISubscriber subscriber : subscribers3) {
				subscriber.notifyAlert(componentID);
				System.out.println("[ManagerImpl]: notificato subscribers3["+i+"]");
				i++;
			}
		}else if(componentID == 4) {
			for(ISubscriber subscriber : subscribers4) {
				subscriber.notifyAlert(componentID);
				System.out.println("[ManagerImpl]: notificato subscribers4["+i+"]");
				i++;
			}
		}else if(componentID == 5) {
			for(ISubscriber subscriber : subscribers5) {
				subscriber.notifyAlert(componentID);
				System.out.println("[ManagerImpl]: notificato subscribers5["+i+"]");
				i++;
			}
		}else{
			System.out.println("[ManagerImpl]: invalid componentID");
		}
		
	}

	@Override
	public void subscribe(int componentID, int porta) throws RemoteException {
		// TODO Auto-generated method stub
		ISubscriber subscriber = new SubscriberProxy(porta);
		if(componentID == 1) {
			subscribers1.add(subscriber);
			System.out.println("[ManagerImpl]: aggiunto nuovo subscriber 1");
		}else if(componentID == 2) {
			subscribers2.add(subscriber);
			System.out.println("[ManagerImpl]: aggiunto nuovo subscriber 2");
		}else if(componentID == 3) {
			subscribers3.add(subscriber);
			System.out.println("[ManagerImpl]: aggiunto nuovo subscriber 3");
		}else if(componentID == 4) {
			subscribers4.add(subscriber);
			System.out.println("[ManagerImpl]: aggiunto nuovo subscriber 4");
		}else if(componentID == 5) {
			subscribers5.add(subscriber);
			System.out.println("[ManagerImpl]: aggiunto nuovo subscriber 5");
		}else{
			System.out.println("[ManagerImpl]: invalid componentID");
		}
	}

}
