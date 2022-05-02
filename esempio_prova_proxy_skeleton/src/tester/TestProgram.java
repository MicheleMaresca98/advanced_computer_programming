package tester;

import coda.*;
import codaimpl.*;

public class TestProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int size = 5;
		// Instanziare una coda circolare SENZA sincronizzazione
		CodaCircolare coda = new CodaCircolare(size);
		
		// Instanziare uno dei 'wrapper' (decorator) responsabile della sincronizzazione
		// CodaWrapperSynchr wrapper = new CodaWrapperSynchr(coda);
		// CodaWrapperSem wrapper = new CodaWrapperSem(coda);
		CodaWrapperLock wrapper = new CodaWrapperLock(coda);
		
		int nthreads = 100;
		
		// Creare un array di 100 thread
		Thread[] threads = new Thread[nthreads];
		
		// Instanziare ed avviare 50 thread di inserimento
		
		// Instanziare ed avviare 50 thread di prelievo
		for(int i = 0; i < nthreads; i++) {
			if(i%2 == 0)
				threads[i] = new WorkerThread(wrapper, true);
			else
				threads[i] = new WorkerThread(wrapper, false);
			threads[i].start();
		}
		
		// Attendere la terminazione dei thread
		for(int i = 0; i < nthreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
