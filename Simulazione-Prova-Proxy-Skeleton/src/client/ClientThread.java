package client;

import interfaces.IGestore;

public class ClientThread extends Thread {
	
	private IGestore gestore;
	private int nrequests;
	
	public ClientThread(IGestore gestore, int nrequests) {
		this.gestore = gestore;
		this.nrequests = nrequests;
	}
	
	public void run() {
		boolean limitazione = false;
		for(int i=0; i<nrequests; i++) {
			try {
				Thread.sleep(1000);
				int matricola = (int)(Math.random()*100);
				if(Math.random() < 0.5) {
					limitazione = true;
				}else {
					limitazione = false;
				}
				gestore.prenota(matricola, limitazione);
				//System.out.println("[ClientThread]: ha prenotato con matricola " + matricola + 
				//		" e limitazione " + limitazione);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}
