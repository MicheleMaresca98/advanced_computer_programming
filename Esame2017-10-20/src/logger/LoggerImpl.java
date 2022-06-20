package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import interfaces.ILogger;

public class LoggerImpl implements ILogger {

	@Override
	public void stampa(int value) {
		// TODO Auto-generated method stub
		try {
			System.out.println("[LoggerImpl] stampa valore " + value);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("./stampe.txt", true)));
			pw.println("Valore: " + value);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
