package subscriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import interfaces.ISubscriber;

public class SubscriberImpl implements ISubscriber {
	
	private String pathfile;
	
	public SubscriberImpl(String pathfile) {
		this.pathfile = pathfile;
	}

	@Override
	public void notifyAlert(int criticality) {
		// TODO Auto-generated method stub
		System.out.println("[SubscriberIml]: ha ricevuto notifica alert con criticality " + criticality);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));
			pw.println("Criticality: " + criticality);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
