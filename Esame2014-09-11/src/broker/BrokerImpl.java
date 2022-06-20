package broker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IAgenzia;
import interfaces.IBroker;

public class BrokerImpl extends UnicastRemoteObject implements IBroker{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5044645174016262769L;
	
	private Vector<IAgenzia> agenzie;
	private int indice_agenzia;
	private int maxAgenzie;
	
	protected BrokerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		agenzie = new Vector<IAgenzia>();
		indice_agenzia = 0;
		maxAgenzie = 3;
	}

	@Override
	public void sottoscrivi(int port) throws RemoteException {
		// TODO Auto-generated method stub
		IAgenzia agenzia = new AgenziaProxy(port);
		agenzie.add(agenzia);
		System.out.println("[BrokerImpl]: agenzia alla porta " + port + " sottoscritta");
		
	}

	@Override
	public void sottoponi(int tipoOperazione, int quantita) throws RemoteException {
		// TODO Auto-generated method stub
		// tipoOperazione 1=acquista, 2=vendi
		int index = nextIndexAgenzia();
		System.out.println("[BrokerImpl]: indice agenzia " + index);
		if(tipoOperazione == 1) {
			agenzie.get(index).acquista(quantita);
			System.out.println("[BrokerImpl]: acquistati " + quantita + " biglietti");
		}else if(tipoOperazione == 2) {
			agenzie.get(index).vendi(quantita);
			System.out.println("[BrokerImpl]: venduti " + quantita + " biglietti");

		}else {
			System.out.println("[BrokerImpl]: tipoOperazione invalido");
		}

	}
	
	private int nextIndexAgenzia() {
		indice_agenzia++;
		int indice = indice_agenzia%maxAgenzie;
		return indice;
	}

}
