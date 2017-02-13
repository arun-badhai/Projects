package eStore;

public class Orders {
	int idOrders;
	String idCustomer;
	String idProduct;
	String shippingAddress;
	String delivery;
	float Price;
	int qty;
	Boolean deliveryStatus;
	
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	
	public Boolean getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(Boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public int getIdOrders() {
		return idOrders;
	}
	public void setIdOrders(int idOrders) {
		this.idOrders = idOrders;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	} 

}
