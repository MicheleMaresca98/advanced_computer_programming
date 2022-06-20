package broker;

import java.util.Vector;

import interfaces.IBroker;
import interfaces.ISportello;

public class BrokerImpl implements IBroker {
	
	private Vector<ISportello> sportelli;
	
	public BrokerImpl() {
		sportelli = new Vector<ISportello>();
	}

	@Override
	public void sottoscrivi(int portSportello) {
		// TODO Auto-generated method stub
		// System.out.println("[BrokerImpl]: sottoscrizion sportello con port " + portSpoertello + " in corso...");
		ISportello sportello = new SportelloProxy(portSportello);
		sportelli.add(sportello);
		System.out.println("[BrokerImpl]: ha sottoscritto sportello con port " + portSportello);
	}

	@Override
	public boolean inoltraRichiesta(int id_cliente) {
		// TODO Auto-generated method stub
		boolean esito = false;
		int i=0;
		while((!esito) && (i<sportelli.size())) {
			esito = sportelli.get(i).serviRichiesta(id_cliente);
			i++;
		}
		System.out.println("[BrokerImpl]: ha inoltrato la richiesta di id_cliente " + id_cliente +
				" con esito " + esito);

		return esito;
	}

}
