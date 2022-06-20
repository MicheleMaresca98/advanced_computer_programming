package sensor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import coda.Coda;

public class TExecutor extends Thread{
	
	private Coda coda;
	private String pathfile;
	
	public TExecutor(Coda coda, String pathfile) {
		this.coda = coda;
		this.pathfile = pathfile;
	}
	
	public void run() {
		try {
			while(true) {
				Thread.sleep(10000);
				Vector<String> comandi = coda.takeAll();
			
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));
			
				for(String comando : comandi) {
					System.out.println("[TExecutor]: takes " + comando);
					pw.println("Comando: " + comando);
					pw.flush();
				}
				
				pw.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
