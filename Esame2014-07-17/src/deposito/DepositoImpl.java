package deposito;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import interfaces.IDeposito;

public class DepositoImpl implements IDeposito {
	
	private String pathfile;
	
	public DepositoImpl(String pathfile) {
		this.pathfile = pathfile;
	}

	@Override
	public synchronized boolean deposita(int id_articolo) {
		// TODO Auto-generated method stub
		boolean esito = false;
		System.out.println("[DepositoImpl]: deposita id_articolo: " + id_articolo);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(pathfile, true)));
		
			pw.println("id_articolo: " + id_articolo);
			pw.flush();
			pw.close();
			esito = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esito;
	}

}
