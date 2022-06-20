package magazzino;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import coda.CodaCircolare;
import coda.CodaWrapperLock;
import interfaces.IClient;
import interfaces.IMagazzino;

public class MagazzinoImpl extends UnicastRemoteObject implements IMagazzino {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7868721811931964571L;
	
	private CodaWrapperLock coda;
	private Vector<IClient> clients;
	private int currentIndex;

	protected MagazzinoImpl(int size) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		coda = new CodaWrapperLock(new CodaCircolare(size));
		clients = new Vector<IClient>();
		currentIndex = 0;
	}

	@Override
	public void sottoscrivi(IClient client) throws RemoteException {
		// TODO Auto-generated method stub
		clients.add(client);
		System.out.println("[MagazzinoImpl]: nuovo client sotttoscritto");
		
	}

	@Override
	public void deposita(int id) throws RemoteException {
		// TODO Auto-generated method stub
		coda.deposita(id);
		int index = nextIndex();
		clients.get(index).nuovoArticolo();

		System.out.println("[MagazzinoImpl]: ha depositato l'articolo con id " + id 
				+ " ed ha notificato client numero " + index);
	}

	@Override
	public int preleva() throws RemoteException {
		// TODO Auto-generated method stub
		int id = coda.preleva();
		System.out.println("[MagazzinoImpl]: ha prelevato l'articolo con id " + id);
		return id;
	}
	
	private int nextIndex() {
		int index = currentIndex%clients.size();
		currentIndex++;
		return index;
	}

}
