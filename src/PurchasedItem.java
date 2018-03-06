/*
 * @filename  PurchasedItem.java
 * @author MinyiYang, 040847473
 * @course CST8130 (Data Structures)
 * @Assignment 1
 * @date February 9th, 2018
 * @professor LINDA CRANE
 * @purpose  This class is used for adding Purchased Items
 */
import java.util.Scanner;
import java.util.regex.Pattern;

public class PurchasedItem extends Item{
	private String supplierName;
	
	public PurchasedItem() {
		supplierName = "";
	}
	@Override
	public boolean addItem(Scanner scan) {
		super.addItem(scan);
		System.out.println("Enter the name of the supplier: ");
		supplierName = scan.next();
		while(Pattern.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?",supplierName)) {
			System.out.print("Invalid name...please enter letters\n"+"Enter the name for the item: ");
			supplierName = scan.next();
		}
		System.out.println();
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+"Supplier: "+supplierName+"\n";
	}
	@Override
	public String getContent() {
		return "p "+super.getContent()+" "+supplierName+"\n";
	}
}
