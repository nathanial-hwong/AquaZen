import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
public class TransactionsHandler extends ProductsDatabase{
	int[] productsCount;
	int[] productsBackup;
	String output;
	public TransactionsHandler() {
		super();
		productsCount = new int[super.nProducts];
		productsBackup = new int[super.nProducts];
		for (int i : productsCount) {
			productsCount[i] = 0;
		}
	}
	public void addProduct(int index) {
		productsCount[index]++;
	}
	public void buildOutput() {
		output = "";
		for (int i = 0;i<super.nProducts;i++) {
			if (productsCount[i]>0) {
				output = output.concat(productsCount[i]+"\t"+super.products[i][super.name]+"\t"+super.products[i][super.price]+" \n");
			}
		}
	}
	public void summary() {
		buildOutput();
		StringSelection string = new StringSelection(output);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(string, null);
		System.out.println("-----------------------------------------------\n"+
		"Copied to clipboard: \n"+output+"-----------------------------------------------"
				+ "\nType \"exit\" to close program or \"load\" to go back to the previous order.\n");
		}
	public void clearOrder() {
		for (int i = 0; i<productsCount.length;i++) {
			productsBackup[i] = productsCount[i];
		}
		for (int i = 0; i<productsCount.length;i++) {
			productsCount[i] = 0;
		}
	}
	public void loadBackup() {
		for (int i = 0; i<productsCount.length;i++) {
			productsCount[i] = productsBackup[i];
		}
		System.out.println("Loaded the following order\n-----------------------------------------------\n"+
				output+"-----------------------------------------------"
						+ "\nType \"exit\" to close program.\n");
		
	}
}
