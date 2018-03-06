/*
 * @filename  ManufacturedItem.java
 * @author MinyiYang, 040847473
 * @course CST8130 (Data Structures)
 * @Assignment 1
 * @date February 9th, 2018
 * @professor LINDA CRANE
 * @purpose  This class is used for adding Manufactured Items
 */
import java.util.Scanner;
import java.util.regex.Pattern;

public class ManufacturedItem extends Item{
	private int[] itemsUsed ;
	private int numItems;
	
	public ManufacturedItem() {
		itemsUsed = new int[10];
		numItems = 0;
	}
	@Override
	public boolean addItem(Scanner scan) {
		int code = 0;
		numItems++;
		super.addItem(scan);
		System.out.println("Enter up to 10 codes used in this item\n"+"(-1 to quit):");
		for(int i = 0; i < 10; i++) {
			String input_code = scan.next();
			while(!Pattern.matches("^([1-9]*|-1)$",input_code)) {
				System.out.print("Invalid code...please enter integer greater than 0\n"+"Please enter again :");
				input_code = scan.next();
			}
			code=Integer.parseInt(input_code);	
			if(code == -1) {
				break;
			}
			else {
				itemsUsed[i] = code;
			}
		}
		System.out.println();
		return true;
	}
	@Override
	public String toString() {
		String usedItems="";
		for(int i=0; i<10; i++) {
			if(itemsUsed[i]!=0) {
				usedItems+=itemsUsed[i]+",";
			}
		}
		return super.toString()+"Codes used: "+usedItems+"\n";
	}
	@Override
	public String getContent() {
		String usedItems="";
		for(int i=0; i<10; i++) {
			if(itemsUsed[i]!=0) {
				usedItems+=itemsUsed[i]+" ";
			}
		}
		return "m "+super.getContent()+" "+usedItems+"-1"+"\n";
	}
}
