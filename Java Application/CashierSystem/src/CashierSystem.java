import java.util.Scanner;
public class CashierSystem {
	public static void main(String args[]) {
		long t0;
		long t1;
		t0 = System.currentTimeMillis();
		TransactionsHandler t = new TransactionsHandler();
		t1 = System.currentTimeMillis();
		System.out.println("Program took: "+(t1-t0)+" ms to populate database.\n");
		ui(t);
	}
	public static void ui(TransactionsHandler p) {
		boolean active = true;
		String input = "";
		String convertedInput = "";
		boolean found = false;
		Scanner keyboard = new Scanner(System.in);
		while(active) {
			found = false;
			System.out.println("       --------------\n"+"Scan next or press ENTER to finalize order...\n"+"       --------------\n");
			input = keyboard.nextLine();
			if (input.equals("exit")) {
				active=false;
				break;
				}
			else if (input.equals("")) {
				p.summary();
				p.clearOrder();
				continue;
			}
			else if (input.equals("load")) {
				p.loadBackup();
				continue;
			}
			else if(input.contains(".")&&input.contains("E+")) {
				convertedInput = toBarcode(input);
			}
			for (int i = 0;i<p.nProducts;i++) {
				if (p.products[i][p.barcode].contains(input)||p.products[i][p.barcode].contains(convertedInput)) {
					System.out.println("Product name is: "+p.products[i][p.name]+"\n");
					p.addProduct(i);
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("========================================================================\n"+
			"***** ERROR PRODUCT NOT FOUND: Enter manually and update database. *****\n"+
			"========================================================================");
			}
			
		}
		System.out.println("Terminating program...");
		keyboard.close();
	}
	public static String toBarcode(String input) {
		Double temp = 0d;
		temp = Double.parseDouble(input);
		return((Long)temp.longValue()).toString();
	}
}

