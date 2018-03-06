/*
 * @filename  Item.java
 * @author MinyiYang, 040847473
 * @course CST8130 (Data Structures)
 * @Assignment 1
 * @date February 9th, 2018
 * @professor LINDA CRANE
 * @purpose  This class is used for getting the values from the scanner and validate them
 */
import java.util.Scanner;
import java.util.regex.Pattern;

public class Item {
	private int itemCode;
	private String itemName;
	private int itemQuantity;
	private float itemPrice;
	
	public Item() {
		itemCode = 0;
		itemName = "";
		itemQuantity = 0;
		itemPrice = 0.0f;
	}
	public boolean addItem(Scanner scan) {
		//itemCode
		System.out.print("Enter the code for the item: ");
		String input_code = scan.next();
		while(!Pattern.matches("^[1-9]\\d*$",input_code)) {
			System.out.print("Invalid code...please enter integer greater than 0\n"+"Enter the code for the item: ");
			input_code = scan.next();
		}
		itemCode = Integer.parseInt(input_code);			
		//itemName
		System.out.print("Enter the name for the item: ");
		itemName = scan.next();
		while(Pattern.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?",itemName)) {
			System.out.print("Invalid name...please enter letters\n"+"Enter the name for the item: ");
			itemName = scan.next();
		}
		//itemQuantity
		System.out.print("Enter the quantity for the item: ");
		String input_quantity = scan.next();
		while(!Pattern.matches("^[1-9]\\d*$",input_quantity)) {
			System.out.print("Invalid quantity...please enter integer greater than 0\n"+"Enter the quantity for the item: ");
			input_quantity = scan.next();
		}
		itemQuantity = Integer.parseInt(input_quantity);
		//itemPrice
		System.out.print("Enter the price of the item: ");
		String input_price = scan.next();
		while(!Pattern.matches("([1-9]\\d*(\\.\\d*[1-9])?)|(0\\.\\d*[1-9])",input_price)) {
			System.out.print("Invalid price...please enter float greater than 0\n"+"Enter the price for the item: ");
			input_price = scan.next();
		}
		itemPrice = Float.parseFloat(input_price);
		
		if(itemCode!=0 && itemName !="" && itemQuantity != 0 && itemPrice != 0.0f) {
			return true;
		}
		else
		{
			return false;
		}
	}
	//return result of item
	@Override
	public String toString() {
		return "Item: "+itemCode+" "+itemName+" "+itemQuantity+" price: $"+itemPrice+"\n";
	}
	public boolean updateItem(int amount) {
		if(amount!=0) {
			itemQuantity += amount;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isEqual(Item item) {
		if(this.itemCode==item.itemCode) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean inputCode(Scanner scan) {
		System.out.print("Enter valid item code: ");
		String input_code = scan.next();
		if(!Pattern.matches("^[1-9]\\d*$",input_code)) {
			System.out.print("Code not found in inventory...\n" + 
					"Error...could not buy item");
			return false;
		}
		itemCode = Integer.parseInt(input_code);	
		return true;
	}
	//getQuantity
	public int getQuantity() {
		return itemQuantity;
	}
	//getitemCode
	public int getItemCode() {
		return itemCode;
	}
	//getContent
	public String getContent() {
		return itemCode+" "+itemName+" "+itemQuantity+" "+itemPrice;
	}
}
