/*
 * @filename  Inventory.java
 * @author MinyiYang, 040847473
 * @course CST8130 (Data Structures)
 * @Assignment 1
 * @date February 9th, 2018
 * @professor LINDA CRANE
 * @purpose  This class is used for adding items(PurchasedItem,ManufacturedItem) and storing them in a Item array
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventory {
	private ArrayList <Item> inventory;
	private int numItems;
	File f=new File("inventory.txt");
	
	public Inventory() {
		inventory = new ArrayList<>();
		numItems=0;
	}
	public boolean addItem(Scanner scan) {
			System.out.println("Do you wish to add a purchased item \n"
					+ "(enter P/p) or manufactured (enter anything else)? ");
			String enter = scan.next();
			Item item;
			if(enter.equals("P") || enter.equals("p")) {
				item= new PurchasedItem();
			}
			else {
				item = new ManufacturedItem();	
			}
			item.addItem(scan);
			//check if the item has the same item code is already existed
			if(this.alreadyExists(item)!=-1) {
				System.out.println("Sorry! The item is already existed");
				return false;
			}
			else {
				inventory.add(item);	
				numItems++;
				//store in an order
				for(int i=1; i<inventory.size(); i++){
					for(int j=i;j>0;j--){
						if(inventory.get(j).getItemCode()<inventory.get(j-1).getItemCode()){
							Item temp=inventory.get(j);
							inventory.set(j, inventory.get(j-1));
							inventory.set(j-1, temp);
						}
					}
				}
//				if(inventory.size()>=2)
//					System.out.println(inventory.get(0).getItemCode()+":"+inventory.get(1).getItemCode());
				return true;
			}
	}
	public String toString() {
		String result="";
		for(int i=0;i<numItems;i++) {
			result+=inventory.get(i).toString();
		}
		if(result=="")
		{
			return "Inventory is empaty\n";
		}else {
			return "Inventory:\n"+result;
		}
	}
	public int alreadyExists(Item item) {
		for(int i=0;i<numItems;i++) {
			if(inventory.get(i).isEqual(item)) {
				return i;
			}		
		}
		return -1;
	}
	public boolean updateQuantity(Scanner scan, boolean i) {
		Item item = new Item();
		item.inputCode(scan);

		if(this.alreadyExists(item)==-1) {
			String deal="";
			if(i==true) {
				deal = "buy";
			}
			else {
				deal = "sell";
			}
			System.out.println("Code not found in inventory...\n"+
					"Error...could not "+deal+" item\n");
			return false;
		}
		else {
			System.out.print("Enter valid quantity : ");
			String amount = scan.next();
			while(!Pattern.matches("^[1-9]\\d*$",amount)) {
				System.out.print("Invalid quantity...Please enter a valid quantity : ");
				amount = scan.next();
			}
			
			if(i==true) {
				inventory.get(this.alreadyExists(item)).updateItem(Integer.parseInt(amount));
			}else {
				if(inventory.get(this.alreadyExists(item)).getQuantity()<Integer.parseInt(amount)) {
					System.out.println("Error... could not sell item");
				}
				else {
					inventory.get(this.alreadyExists(item)).updateItem(-Integer.parseInt(amount));
				}
			}
			System.out.println();
			return true;
		}
		
	}
	//save the content to a file
	public boolean saveFile() {
		try {
			 FileWriter fw = new FileWriter(f.getName(), true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter ouput = new PrintWriter(bw);
			 for(int i = 0; i<inventory.size(); i++) {
				 String str = inventory.get(i).getContent();
				 ouput.println(str);
			 }
			 ouput.close();
			 return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}	
	}
	//read the file
	public String readFile() {
		String content="";
		try {
			Scanner fileIn = new Scanner(f);//read the file
			while(fileIn.hasNextLine()) {
				content+=fileIn.nextLine()+"\n";
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}
}
