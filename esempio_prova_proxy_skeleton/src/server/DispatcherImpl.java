package server;

import codaimpl.CodaCircolare;
import codaimpl.CodaWrapperSynchr;

public class DispatcherImpl extends DispatcherSkeleton{
	
	private CodaWrapperSynchr codaWrapper;
	
	public DispatcherImpl() {
		int size = 5;
		CodaCircolare coda = new CodaCircolare(size);
		codaWrapper = new CodaWrapperSynchr(coda);
	}

	@Override
	public void sendCmd(int command) {
		// TODO Auto-generated method stub
		codaWrapper.inserisci(command);
		
	}

	@Override
	public int getCmd() {
		// TODO Auto-generated method stub
		int x = codaWrapper.preleva();
		return x;
	}
	
	

}
