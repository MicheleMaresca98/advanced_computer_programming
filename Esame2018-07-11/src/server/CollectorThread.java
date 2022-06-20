package server;

import java.rmi.RemoteException;

import interfaces.ILogService;

public class CollectorThread extends Thread {
	
	private ILogService logService;
	private int temperatura;
	
	public CollectorThread(ILogService logService, int temperatura) {
		this.logService = logService;
		this.temperatura = temperatura;
	}
	
	public void run() {
		try {
			boolean flag = logService.stampa(temperatura);
			System.out.println("[CollectorThread] stampa temperatura " + temperatura + " con esito " + flag);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
