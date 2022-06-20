package sensor;

import coda.Coda;

public class TManager extends Thread{
	
	private String comando;
	private Coda coda;
	
	public TManager(String comando, Coda coda) {
		this.comando = comando;
		this.coda = coda;
	}
	
	public void run() {
		coda.put(comando);
		System.out.println("[TManager]: puts " + comando);
	}

}
