package esempio;

import java.io.*;

public class FileIO {

	public static void main(String[] args) {
		try {
			// OutputStream / InputStream
			
			FileOutputStream fileOut = new FileOutputStream("./test1.txt");
			BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
			DataOutputStream dataOut = new DataOutputStream(bufOut);
			
			dataOut.writeInt(100);
			dataOut.writeUTF("Testo di prova 1");
			
			dataOut.flush();
			dataOut.close();
			
			System.out.println("Content written to test1.txt");
			
			FileInputStream fileIn = new FileInputStream("./test1.txt");
			DataInputStream dataIn = new DataInputStream(fileIn);
			
			int x = dataIn.readInt();
			String s = dataIn.readUTF();
			
			dataIn.close();
			System.out.println("Reading from test1.txt: " + x + ", " + s);
			
			// Writer / Reader
			
			FileWriter fileWriter = new FileWriter("./test2.txt");
			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
			
			bufWriter.write("Testo di prova esempio 2\n");
			bufWriter.flush();
			
			bufWriter.close();
			
			System.out.println("Content written to test2.txt");
			
			FileReader fileReader = new FileReader("./test2.txt");
			BufferedReader bufReader = new BufferedReader(fileReader);
			
			s = bufReader.readLine();
			
			bufReader.close();
			
			System.out.println("Reading from test2.txt: " + s);
			
			//OutputStream to Writer conversion
			
			FileOutputStream fileOut2 = new FileOutputStream("./test3.txt");
			BufferedWriter bufWriter2 = new BufferedWriter(new OutputStreamWriter(fileOut2));
			
			bufWriter2.write("Testo di prova 3\n");
			bufWriter2.write("Altra linea su file 3\n");
			
			bufWriter2.flush();
			bufWriter2.close();
			
			System.out.println("Content written to test3.txt");
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
