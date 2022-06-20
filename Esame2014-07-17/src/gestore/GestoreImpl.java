package gestore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IDeposito;
import interfaces.IGestore;

public class GestoreImpl extends UnicastRemoteObject implements IGestore {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5041400468306296350L;
	
	private Vector<IDeposito> depositi;
	private int currentIndex;

	protected GestoreImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		depositi = new Vector<IDeposito>();
		currentIndex = 0;
	}

	@Override
	public void sottoscrivi(int portDeposito) throws RemoteException {
		// TODO Auto-generated method stub
		IDeposito deposito = new DepositoProxy(portDeposito);
		depositi.add(deposito);
		System.out.println("[GestoreImpl]: nuovo deposito alla porta " + portDeposito + " sottoscritto");
	}

	@Override
	public boolean richiestaDeposito(int id_articolo) throws RemoteException {
		// TODO Auto-generated method stub
		boolean esito = false;
		esito = depositi.get(nextIndex()).deposita(id_articolo);
		return esito;
	}
	
	private int nextIndex() {
		int index = currentIndex%depositi.size();
		System.out.println("[GestoreImpl]: nextIndex " + index);
		currentIndex++;
		return index;
	}

}
