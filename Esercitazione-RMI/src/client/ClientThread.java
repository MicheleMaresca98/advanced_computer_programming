package client;


import java.rmi.RemoteException;

import interfaces.IGestoreSportello;


public class ClientThread extends Thread {
	
	private int nrequests;
	private IGestoreSportello gestore;
	
	public ClientThread(int requests, IGestoreSportello gestore) {
		this.nrequests = requests;
		this.gestore = gestore;
		
	}
	
	public void run() {
		try {
			for(int i=0; i<this.nrequests; i++) {
				Thread.sleep((int)(1 + Math.random()*3)*1000);
				int idCliente = (int)(1 + Math.random()*100);
				boolean flag = gestore.sottoponiRichiesta(idCliente);
				
				System.out.println("[ClientThread]: Richiesta terminata con esito " + flag);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
