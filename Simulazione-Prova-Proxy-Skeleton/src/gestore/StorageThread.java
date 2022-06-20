package gestore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import coda.Coda;

public class StorageThread extends Thread{
	
	private Coda coda;
	private String pathfile;
	
	public StorageThread(Coda coda, String pathfile) {
		this.coda = coda;
		this.pathfile = pathfile;
	}
	
	public void run() {
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));

			while(true) {
				Thread.sleep(5000);
				for(int i=0; i<coda.size(); i++) {
					int matricola = coda.preleva();
					System.out.println("[StorageThread]: preleva matricola " + matricola);
					pw.println("Matricola: " + matricola);
					pw.flush();
				}
				
			
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
