package com.mercy.fitstore;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FITInventoryApp {

	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		List<FITInventory> arrFITInv = new ArrayList<>();
		//String invFileName = "C:\\Purgeme\\invFile.txt";
		String invFileName = "src\\main\\java\\com\\mercy\\fitstore\\FITStoreInventoryItems.txt";

		readInventoryFromFIle(arrFITInv, invFileName);
		
		int selection = 0;
		
		do {
		
			System.out.println(">>>>> What do you want to do? <<<<<\n\n"+
		                         "1  Add to Inventory\n"+
					             "2  Remove from Inventory\n"+
		                         "3  Print items in Inventory\n"+
					             "4  EXIT\n"
		                       );
			selection = in.nextInt();
			in.nextLine();
		
		switch( selection ) {
		case 1:
			addItemQtyToInventory(arrFITInv);
			break;
		case 2:
			removeItemQtyFromInventory(arrFITInv);
			break;
		case 3:
			printItemsInInventory(arrFITInv);
			break;
		case 4:
			System.out.println("GoodBye!");
			break;
		default:
			System.out.println("Invalid selection. Please try again.\n\n");
		}
		System.out.println();
		
		} while (selection < 4 && selection > 0);
		
		in.close();
		printItemsInInventoryToFile(arrFITInv, invFileName);
	}
	
	public static void readInventoryFromFIle(List<FITInventory> arrInv, String invFN) {
		
		String delimitedBy = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(invFN));
			String aLine = br.readLine(); // throw away the header line
			
			while ((aLine = br.readLine()) != null) {
				String[] s = aLine.split(delimitedBy);
				arrInv.add(new FITInventory(s[0], Integer.parseInt(s[1]),Double.parseDouble(s[2])));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void addItemQtyToInventory(List<FITInventory> arrInv) {
		String invItemName = getInvItem();
		int invItemQty = getInvQty("add to");
		
		for (int i = 0; i<arrInv.size(); i++) {
			FITInventory f = arrInv.get(i);
			if ( f.getItemName().equals(invItemName) ) {
				f.addQtyToInventory(invItemQty);
				break;
			}				
		}
			
	}
	
	public static void removeItemQtyFromInventory(List<FITInventory> arrInv) {
		String invItemName = getInvItem();
		int invItemQty = getInvQty("remove from");

		for (int i = 0; i<arrInv.size(); i++) {
			FITInventory f = arrInv.get(i);
			if ( f.getItemName().equals(invItemName) ) {
				f.removeQtyFromInventory(invItemQty);
				break;
			}				
		}	
		
	}
	
	public static void printItemsInInventory (List<FITInventory> arrInv) {
		System.out.printf("Items\tPer Price\tQty Available\n");
		for(FITInventory f : arrInv) {
			f.println();
		}
		System.out.println();
	}

	public static void printItemsInInventoryToFile(List<FITInventory> arrInv, String fn) {
		String headerLine = "ItemName,ItemQty,ItemPrice";
		String delimitedBy = ",";
		
		try {
			FileWriter fWriter = new FileWriter(fn);
			fWriter.append(headerLine);
			for (FITInventory f : arrInv) {
				fWriter.append("\n");
				fWriter.append(f.getItemName());
				fWriter.append(delimitedBy);
				fWriter.append(String.valueOf(f.getItemQtyInStock()));
				fWriter.append(delimitedBy);
				fWriter.append(String.valueOf(f.getItemPerPrice()));
			}
			fWriter.flush();
			fWriter.close();
		} catch (Exception e) {
			System.out.print("ERROR : " + e.getMessage());
		}
	}
	
	public static String getInvItem() {
		int selection = 0;
		boolean firstTime = true;
		
		do {
			if (firstTime == true)
				firstTime = false;
			else
				System.out.println("Invalid selection. Please try again.\n");
			
			System.out.println("Please select an inventory item:\n"+
		                         "1  Candy\n"+
					             "2  Chip\n"+
		                         "3  Soda\n"+
					             "4  Water\n"
		                         );
			selection = in.nextInt();
			in.nextLine();
		} while (selection > 5 || selection < 1);
		
		switch( selection ) {
		case 1:
			return "Candy";
		case 2:
			return "Chip";
		case 3:
		    return "Soda";
		case 4:
			return "Water";
		}
		
		return "";
        
	}
	
	public static int getInvQty(String s) {
		System.out.println("Enter the quantity of the item to "+s+" the inventory");
		while (!in.hasNextInt()) {
			System.out.println("Invalid value. Pelase entner the quantiy as a whole number! ");
			in.nextLine();
		}
		
		int itemQty = in.nextInt();
		in.nextLine();
		return itemQty;
	}
}
