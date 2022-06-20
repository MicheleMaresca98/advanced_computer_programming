package client;

import java.rmi.RemoteException;

import interfaces.IDispatcher;
import server.Reading;

public class GeneratorThread extends Thread{
	
	private IDispatcher dispatcher;
	private int nrequests;
	
	public GeneratorThread(IDispatcher dispatcher, int nrequests) {
		this.dispatcher = dispatcher;
		this.nrequests = nrequests;
	}
	
	public void run() {
		try {
			String tipo;
			for(int i=0; i<nrequests; i++) {
				if((int)(Math.random()*2) == 0) {
					tipo = "temperatura";
				}else if((int)(Math.random()*2) == 1) {
					tipo = "pressione";
				}else {
					System.out.println("[GeneratorThread] errore nel tipo, messo di default pressione.");
					tipo = "pressione";
				}
				int valore = (int)(Math.random()*51);
				Reading reading = new Reading(tipo, valore);
				dispatcher.setReading(reading);
				System.out.println("[GeneratorThread] invocato setReading con Reading di tipo: "+tipo+" e valore: "+valore);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
