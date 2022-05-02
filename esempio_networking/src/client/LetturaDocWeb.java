package client;

import java.io.*;
import java.net.*;

public class LetturaDocWeb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL page = new URL("http://www.scuolapsb.unina.it/");
			URLConnection conn = page.openConnection();
			
			conn.connect();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = r.readLine();
			while(line != null) {
				System.out.println(line);
				line = r.readLine();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
