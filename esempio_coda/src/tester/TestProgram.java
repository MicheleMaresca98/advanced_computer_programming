package tester;

import coda.*;
import codaimpl.*;

public class TestProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		// Instanziare una coda circolare SENZA sincronizzazione
		Coda codacircolare = new CodaCircolare(5);
		
		// Instanziare uno dei 'wrapper' (decorator) responsabile della sincronizzazione
		Coda codawrappersync = new CodaWrapperSem(codacircolare);
		
		int nthreads = 100;
		
		// Creare un array di 100 thread
		WorkerThread [] threads = new WorkerThread[nthreads];
		
		// Instanziare ed avviare 50 thread di inserimento
		
		// Instanziare ed avviare 50 thread di prelievo
		
		for(int i=0;i<nthreads; i++) {
			if( i%2 == 0 )
				threads[i] = new WorkerThread(codawrappersync, true);
			else
				threads[i] = new WorkerThread(codawrappersync, false);
			
			threads[i].start();
		}
		
		// Attendere la terminazione dei thread
		
		for(int i = 0;i < nthreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
