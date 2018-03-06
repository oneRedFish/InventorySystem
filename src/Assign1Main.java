/*
 * @filename  Assign1Main.java
 * @author MinyiYang, 040847473
 * @course CST8130 (Data Structures)
 * @Assignment 1
 * @date February 9th, 2018
 * @professor LINDA CRANE
 * @purpose  This class is used for showing the menu and let users choose one function to run
 */
import java.util.Scanner;
import java.util.regex.Pattern;

public class Assign1Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Inventory inventory=new Inventory();
		String choose="";
		while(!choose.equals("7")) {
			choose=showMenu( scan, choose);
			while(!Pattern.matches("^[1-7]\\d*$",choose)){
				System.out.println("Invalid entry please input from 1 to 7\n");
				choose=showMenu( scan, choose);
			}
			switch(Integer.parseInt(choose)) {
					case 1:
							inventory.addItem(scan);
							break;
					case 2:
							System.out.println(inventory.toString());
							break;
					case 3:
							inventory.updateQuantity(scan, true);
							break;
					case 4:
							inventory.updateQuantity(scan, false);
							break;
					case 5:
							inventory.saveFile();
							break;
					case 6:
							System.out.println(inventory.readFile());
							break;
					case 7:
							break;
			}
		}
	}
	//show the menu
	public static String showMenu(Scanner scan,String choose) {
		System.out.println("Enter 1 to add an item to inventory \n" + 
				"2 to display current inventory\n" + 
				"3 buy some of an item \n" + 
				"4 sell some of an item  \n" + 
				"5 save the contents of the inventory ArrayList to a file \n" + 
				"6 read the contents of a file to the inventory ArrayList \n"+
				"7 to quit");
		choose = scan.next();
		return choose;
	}

}
