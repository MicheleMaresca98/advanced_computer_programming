package client;

import java.rmi.RemoteException;

import interfaces.IBroker;

public class ClientThread extends Thread{
	
	private IBroker broker;
	private int R;
	
	public ClientThread(IBroker broker, int R) {
		this.broker = broker;
		this.R = R;
	}
	
	public void run() {
		int tipoOperazione = 0;
		try {
			for(int i=0; i<R; i++) {
				Thread.sleep((int)(1 + Math.random()*3)*1000);
				
				if(Math.random() < 0.5) {
					tipoOperazione = 1;
				}else {
					tipoOperazione = 2;
				}
				
				int quantita = (int)(1 + Math.random()*5);
				broker.sottoponi(tipoOperazione, quantita);
				System.out.println("[ClientThread]: ha sottoposto tipoOperazione " + tipoOperazione + 
						" (1=acquista, 2=vendi) quantita " + quantita);
				
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
