package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import interfaces.IPrinter;

public class PrinterImpl implements IPrinter{
	
	private Lock lock;
	private String file;
	
	public PrinterImpl(String file) {
		lock = new ReentrantLock();
		this.file = file;
	}

	@Override
	public boolean print(String docName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		if(!lock.tryLock()) {
			System.out.println("[PrinterImpl]: printer occupata, richiesta ha ottenuto risposta " + flag);
			return flag;
		}
		
		
		
		try {
			Thread.sleep((int)(5 + Math.random()*6)*1000);
		
			System.out.println("[PrinterImpl]: stampa file docName: " + docName);
			
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			pw.println("docName: " + docName);
			pw.flush();
			
			flag = true;
			
			pw.close();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		return flag;
	}

}
