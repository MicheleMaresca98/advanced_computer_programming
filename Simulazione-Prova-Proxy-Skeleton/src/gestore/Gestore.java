package gestore;

import coda.CodaCircolare;
import coda.CodaWrapperLock;

public class Gestore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathfile = "./elenco_prenotati.txt";
		int size = 10;
		int port = 9000;
		CodaWrapperLock coda = new CodaWrapperLock(new CodaCircolare(size));
		
		StorageThread storageThread = new StorageThread(coda, pathfile);
		storageThread.start();
		GestoreImpl gestore = new GestoreImpl(port, coda);
		System.out.println("[Gestore]: avviato gestore e avviato StorageThread");
		gestore.runSkeleton();

	}

}
