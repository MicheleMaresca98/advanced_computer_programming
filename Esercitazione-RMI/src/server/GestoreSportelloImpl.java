package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IGestoreSportello;
import interfaces.ISportello;


public class GestoreSportelloImpl extends UnicastRemoteObject implements IGestoreSportello{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<ISportello> listaSportelli;

	protected GestoreSportelloImpl() throws RemoteException{
		// TODO Auto-generated constructor stub
		listaSportelli = new Vector<ISportello>();
	}

	@Override
	public boolean sottoponiRichiesta(int idCliente) throws RemoteException{
		// TODO Auto-generated method stub
		boolean flag = false;
		int i=0;
		while((flag==false) && (i<listaSportelli.size()) ) {
			flag = listaSportelli.get(i).serviRichiesta(idCliente);
			i++;
		}
		
		System.out.println("[GestoreSportelloImpl]: Richiesta dal client "+ idCliente + " terminata con esito "+flag);
		
		return flag;
	}

	@Override
	public void sottoscrivi(ISportello sportello) throws RemoteException{
		// TODO Auto-generated method stub
		listaSportelli.add(sportello);
		System.out.println("[GestoreSportelloImpl]: Sottoscritto nuovo sportello");
		
		
	}

}
