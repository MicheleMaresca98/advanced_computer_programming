package org.prova;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileIOApp {

	public static void main(String[] args) throws IOException {
		try {
			File filename = new File("text.txt");
			FileOutputStream fos = new FileOutputStream(filename);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			DataOutputStream dos = new DataOutputStream(bos);
			//dos.writeInt(44);
			String s = "Questo è un test";
			dos.writeUTF(s);
			dos.flush(); //scrittura del contenuto del buffer
			bos.close();
			dos.close();
			fos.close();
		}catch(IOException e) {
			
		}
		InputStream in = null;
		try {
			File filename = new File("text.txt");
			in = new FileInputStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}catch(IOException e) {
			System.err.println(e);
		}finally {
			if (in != null) {
				in.close();
			}
		}

	}

}
