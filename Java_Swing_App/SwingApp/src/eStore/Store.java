package eStore;

import java.io.InputStream;

public class Store 
{
	String idItem;
	String itemName;
	String itemDescription; 
	int stock;
	boolean inStock; 
	float price;
	float discount;
	String dealerName;
	String isnew;
	String icon_filename;
	boolean selected;
	byte[] icon;
	int selectedQty;
	
	public int getSelectedQty() {
		return selectedQty;
	}
	public void setSelectedQty(int selectedQty) {
		this.selectedQty = selectedQty;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public String getIdItem() {
		return idItem;
	}
	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String isIsnew() {
		return isnew;
	}
	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}
	public String getIcon_filename() {
		return icon_filename;
	}
	public void setIcon_filename(String icon_filename) {
		this.icon_filename = icon_filename;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	} 
	
	

}