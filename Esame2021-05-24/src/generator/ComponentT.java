package generator;

import java.rmi.RemoteException;

import alertnotification.AlertNotification;
import interfaces.IManager;

public class ComponentT extends Thread {
	
	private IManager manager;
	
	public ComponentT(IManager manager) {
		this.manager = manager;
	}
	
	public void run() {
		int componentID = (int)(1 + Math.random()*5);
		int criticality = (int)(1 + Math.random()*3);
		AlertNotification alertNotification = new AlertNotification(componentID, criticality);
		try {
			manager.sendNotification(alertNotification);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[ComponentT]: invia notifica con componentID " + componentID + " e criticality " + criticality);
	}

}
