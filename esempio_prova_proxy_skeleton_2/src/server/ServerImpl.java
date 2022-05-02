package server;

import codaimpl.CodaCircolare;
import codaimpl.CodaWrapperSynchr;

public class ServerImpl extends ServerSkeleton{
	
	private CodaWrapperSynchr codaWrapperLaptop;
	private CodaWrapperSynchr codaWrapperSmartphone;
	
	public ServerImpl() {
		
		int sizeLaptop = 5;
		CodaCircolare codaLaptop = new CodaCircolare(sizeLaptop);
		codaWrapperLaptop = new CodaWrapperSynchr(codaLaptop);
		
		int sizeSmartphone = 5;
		CodaCircolare codaSmartphone = new CodaCircolare(sizeSmartphone);
		codaWrapperSmartphone = new CodaWrapperSynchr(codaSmartphone);
		
	}

	@Override
	public void deposita(String articolo, int id) {
		// TODO Auto-generated method stub
		System.out.println("[ServerImpl]: deve depositare articolo "+articolo);
		if(articolo.equals("laptop")) {
			codaWrapperLaptop.inserisci(id);
		}else if(articolo.equals("smartphone")) {
			codaWrapperSmartphone.inserisci(id);
		}else {
			System.out.println("[ServerImpl]: invalid articolo");
		}
		
		
	}

	@Override
	public int preleva(String articolo) {
		// TODO Auto-generated method stub
		int id=0;
		if(articolo.equals("laptop")) {
			id = codaWrapperLaptop.preleva();
		}else if(articolo.equals("smartphone")) {
			id = codaWrapperSmartphone.preleva();
		}else {
			System.out.println("[ServerImpl]: invalid articolo");
		}
		return id;
	}

}
