package codaimpl;

import coda.*;

public class CodaWrapperSynchr extends CodaWrapper {

	
	public CodaWrapperSynchr( Coda c ){
		super (c);		
	}
	
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con blocchi synchronized
		synchronized(this.coda) {
			while(coda.full()) {
				try {
					coda.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			coda.inserisci(i);
			coda.notifyAll();
		}
			
		
	}
	
	
	public int preleva(){
		int x=0;
		
		// Implementare sincronizzazione con blocchi synchronized
		synchronized(this.coda) {
			while(coda.empty()) {
				try {
					coda.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			x = coda.preleva();
			coda.notifyAll();
		}
			
		return x;
	}
	
}
