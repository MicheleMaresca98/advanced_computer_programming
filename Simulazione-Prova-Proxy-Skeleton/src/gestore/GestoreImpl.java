package gestore;

import coda.CodaWrapperLock;

public class GestoreImpl extends GestoreSkeleton {
	
	private CodaWrapperLock coda;
	
	public GestoreImpl(int port, CodaWrapperLock coda) {
		super(port);
		this.coda = coda;
		
	}

	@Override
	public boolean prenota(int matricola, boolean limitazione) {
		// TODO Auto-generated method stub
		boolean esito = false;
		if(limitazione) {
			esito = coda.depositaLimitazione(matricola);

		}else {
			coda.deposita(matricola);
			esito = true;
		}
		System.out.println("[GestoreImpl]: prenotazione con matricola " + matricola +
				" e limitazione " + limitazione + " terminata con esito " + esito);
		return esito;

	}

}
