package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import interfaces.IDispatcher;
import interfaces.IPrinter;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7207147411189077378L;
	
	private Vector<IPrinter> printers;

	protected DispatcherImpl() throws RemoteException {
		printers = new Vector<IPrinter>();
	}

	@Override
	public boolean printRequest(String docName) throws RemoteException {
		// TODO Auto-generated method stub
		int i = 0;
		boolean flag = false;
		while((!flag) && (i<printers.size())) {
			//PrinterProxy printer = (PrinterProxy) printers.get(i);
			//flag = printer.print(docName);
			flag = printers.get(i).print(docName);
			i++;
		}
		System.out.println("[DispatcherImpl]: printRequest docName: "+docName+" ha restituito esito " + flag);
		return flag;
	}

	@Override
	public void addPrinter(String ip, int port) throws RemoteException {
		// TODO Auto-generated method stub
		PrinterProxy printer = new PrinterProxy(ip, port);
		printers.add(printer);
		System.out.println("[DispatcherImpl] nuova printer aggiunta all'indirizzo "+ip+" e porta "+ port);
		
	}

}
