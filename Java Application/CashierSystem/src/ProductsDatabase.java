import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ProductsDatabase {
	public String[][] products;
	public int nProducts;
	public String[] attributes;
	int barcode,name,weight,volume,price;
	String database;
	
	public ProductsDatabase(){
		Scanner read = null;
		Scanner init = null;
		database = "database.csv";
		try {
			read = new Scanner(new FileInputStream(database));
			init = new Scanner(new FileInputStream(database));
		}
		catch(IOException e) {
			System.out.println("File reading or write error"+e);
			System.exit(0);
		}
		getAttributesList(init);
		getProductQty(init);
		barcode = this.getAttIndex("Variant Barcode");
		name = this.getAttIndex("Handle");
		weight = this.getAttIndex("Variant Grams");
		volume = this.getAttIndex("Option1 Value");
		price = this.getAttIndex("Variant Price");
		products = new String[nProducts][attributes.length];
		populateDatabase(read);
		System.out.println("Found "+nProducts+" products from file "+database);
		read.close();
		init.close();
	}
	public void getProductQty(Scanner s) {
		int i = 0;
		String temp;
		while(s.hasNextLine()) {
			temp = s.nextLine();
			i++;
		}
		nProducts = i;
	}
	public void getAttributesList(Scanner s) {
		attributes = s.nextLine().split(",");
	}
	public void populateDatabase(Scanner s) {
		int i = 0;
		s.nextLine();
		Long temp;
		//String tempTitle = "";
		while (s.hasNextLine()) {
			products[i] = s.nextLine().split(",");
			//if (products[i][name].equals(""))products[i][name] = tempTitle+"-"+products[i][weight]+"-"+products[i][volume];
			
			//else tempTitle = products[i][name];
			i++;
		}
	}
	public int getAttIndex(String s) {
		for (int i = 0;i<attributes.length;i++) {
			if (s.equals(attributes[i]))return i;
		}
		return -1;
	}
}
