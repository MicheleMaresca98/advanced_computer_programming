package client;

import interfaces.IBroker;

public class ClientThread extends Thread{
	
	private IBroker broker;
	private int R;
	
	public ClientThread(IBroker broker, int R) {
		this.broker = broker;
		this.R = R;
	}
	
	public void run() {
		for(int i=0; i<R; i++) {
			try {
				Thread.sleep((int)(1 + Math.random()*3)*1000);
				int id_cliente = (int)(1 + Math.random()*100);
				broker.inoltraRichiesta(id_cliente);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
