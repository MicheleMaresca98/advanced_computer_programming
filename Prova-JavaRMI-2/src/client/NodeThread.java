package client;

import java.rmi.RemoteException;

import interfaces.IDispatcher;

public class NodeThread extends Thread{
	
	private IDispatcher dispatcher;
	private int nrequests;
	
	public NodeThread(IDispatcher dispatcher, int nrequests) {
		this.dispatcher = dispatcher;
		this.nrequests = nrequests;
	}
	
	public void run() {
		try {
			
			for(int i=0; i<nrequests; i++) {
				Thread.sleep(3000);
				String docName = new String("doc"+(int)(1 + Math.random()*50));
				dispatcher.printRequest(docName);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
