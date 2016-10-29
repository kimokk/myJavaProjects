package com.mercy.fitstore;

public class FITInventory {

	private String itemName;
	private int itemQtyInStock;
	private double itemPerPrice;
	
	public FITInventory(String itemName, int itemQty, double itemPrice) {
		this.itemName = itemName;
		itemQtyInStock = itemQty;
		itemPerPrice = itemPrice;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getItemQtyInStock() {
		return itemQtyInStock;
	}

	public double getItemPerPrice() {
		return itemPerPrice;
	}
	
	public void setItemPerPrice(double itemPerPrice) {
		this.itemPerPrice = itemPerPrice;
	}
	
	public void addQtyToInventory(int itemQtyToAdd) {
		itemQtyInStock = itemQtyInStock + itemQtyToAdd;
	}
	
	public void removeQtyFromInventory(int itemQtyToRemove) {
		if (itemQtyInStock == 0 || itemQtyToRemove > itemQtyInStock)
			System.out.println("No action taken!\nItem Qty in stock : "+ itemQtyInStock +
					           "\nItem Qty requested to remove : "+ itemQtyToRemove
					           );
		else
		    itemQtyInStock = itemQtyInStock - itemQtyToRemove;		
	}
	
	public void println() {
		System.out.printf("%s\t%7.2f\t%15d\n", itemName, itemPerPrice, itemQtyInStock);
	}
	
	public String toString() {
		return String.format("%s\t%.2f\t%10d\n", itemName, itemPerPrice, itemQtyInStock);
	}
}
