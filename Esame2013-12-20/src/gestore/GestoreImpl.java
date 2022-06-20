package gestore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IGestore;
import interfaces.ISportello;

public class GestoreImpl extends UnicastRemoteObject implements IGestore {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8360456771125662607L;
	private Vector<ISportello> sportelli;
	
	protected GestoreImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.sportelli = new Vector<ISportello>();

	}
	

	@Override
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException {
		// TODO Auto-generated method stub
		boolean esito = false;
		int i=0;
		while( (!esito) && (i<sportelli.size())) {
			esito = sportelli.get(i).serviRichiesta(idCliente);
			i++;
		}
		return esito;
	}

	@Override
	public void sottoscrivi(ISportello sportello) throws RemoteException {
		// TODO Auto-generated method stub
		sportelli.add(sportello);
		System.out.println("[GestoreImpl]: nuovo sportello sottoscritto");
		
	}

}
