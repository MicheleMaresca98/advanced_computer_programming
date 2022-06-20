package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class Compute {
	
	private Semaphore maxThreads;
	
	public Compute() {
		this.maxThreads = new Semaphore(5);
	}
	
	public int getResult(String operazione, int operando1, int operando2) {
		
		
		
		int result = 0;
		try {
			maxThreads.acquire();
			
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cmplog.txt", true)));
			Thread.sleep((int)(1 + Math.random()*5)*1000);
			if(operazione.equals("SUM")) {
				result = operando1 + operando2;
				System.out.println("SUM, " + operando1 + ", " + operando2 + ", " + result);
				pw.println("SUM, " + operando1 + ", " + operando2 + ", " + result);
				pw.flush();
				pw.close();
			}else if(operazione.equals("MUL")) {
				result = operando1 * operando2;
				System.out.println("MUL, " + operando1 + ", " + operando2 + ", " + result);
				pw.println("MUL, " + operando1 + ", " + operando2 + ", " + result);
				pw.flush();
				pw.close();
			}else {
				System.out.println("[Compute]: operazione invalida");
			}
			
			maxThreads.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
