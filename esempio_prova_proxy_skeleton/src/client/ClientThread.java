package client;

import dispatcher.IDispatcher;

public class ClientThread extends Thread{
	
	private IDispatcher dispatcherProxy;
	
	public ClientThread(IDispatcher dispatcher) {
		this.dispatcherProxy = dispatcher;
	}
	
	public void run() {
		try {
			Thread.sleep((long) (2000 + Math.random()*2000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 3; i++) {
			int command = (int)(Math.random()*4);
			System.out.println("[ClientThread]: il client ha inviato il comando " + command);
			dispatcherProxy.sendCmd(command);
		}
		
		
	}

}
