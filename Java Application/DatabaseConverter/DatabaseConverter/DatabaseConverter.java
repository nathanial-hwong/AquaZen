import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import com.opencsv.CSVReader;

public class DatabaseConverter {
	public static void main(String[] args) {
		Scanner s = null;
		PrintWriter p = null;
		String[] temp;
		int[] attributes;
		String[] vendor = {"","",""};
		//open files to read and write from
		try {
			s = new Scanner(new FileInputStream("products_export_1.csv"));
		}
		catch(IOException e) {
			System.out.println("File reading error");
			System.exit(0);
		}
		try {
			File database = new File("database.txt");
			p = new PrintWriter(new FileOutputStream(database));
		}
		catch(IOException e) {
			System.out.println("File writing error, Check if database file is open. Now exiting...");
			System.exit(0);
		}
		//get the column for data to extract
		temp = s.nextLine().split(",");
		attributes = getIndexes(temp);
		p.println(temp[attributes[0]]+","+temp[attributes[1]]+","+temp[attributes[2]]+","+temp[attributes[3]]+","+temp[attributes[4]]+","+"Supplier");
		//to keep track of entries read and entries written
		int x = 1;
		int numWithCode = 0;
		//iterate through entire exports files
		String output;
		while(s.hasNextLine()) {
			temp = s.nextLine().split(",");
			if (temp[attributes[3]]!="(Blanks)"&&temp[attributes[3]].length()>0) {
				output = (temp[attributes[0]]+","+temp[attributes[1]]+","+temp[attributes[2]]+","+temp[attributes[3]]+","+temp[attributes[4]]);
				if(temp[attributes[1]].length()>0) {
				}
				p.println(output);
				numWithCode++;
			}
			x++;
		}
		
		System.out.println("There are "+numWithCode+"products with barcodes.");
		/**try {
			System.out.println(readAllLines());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
		s.close();
		p.close();
		
	}
	//finds the column for useful data
	public static int[] getIndexes(String[] s) {
		int[] att = new int[5];
		for (int i = 0; i<s.length;i++) {
			if(s[i].equals("Handle"))att[0] = i;
			if(s[i].equals("Vendor"))att[1] = i;
			if(s[i].equals("Variant Price"))att[2] = i;
			if(s[i].equals("Variant Barcode"))att[3] = i;
			if(s[i].equals("Cost per item"))att[4] = i;
		}
		return att;
		
	}
	/**public static List<String[]> readAllLines() throws Exception {
		Path filepath = Paths.get("database.csv");
	    try (Reader reader = Files.newBufferedReader(filepath)) {
	        try (CSVReader csvReader = new CSVReader(reader)) {
	            return csvReader.readAll();
	        }
	    }
	}**/
	
}

