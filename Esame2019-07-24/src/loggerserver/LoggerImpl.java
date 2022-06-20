package loggerserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import interfaces.ILogger;

public class LoggerImpl implements ILogger {

	@Override
	public synchronized void registraDato(int dato) {
		// TODO Auto-generated method stub
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./saved.txt", true)));
			pw.println("Saved: " + dato);
			pw.flush();
			System.out.println("[LoggerImpl]: ha registrato " + dato);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
