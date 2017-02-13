package eStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import eStore.Employee;
import javax.swing.ImageIcon;

import org.omg.CORBA.portable.InputStream;

import eStore.MakeConnection;
import eStore.Customer;

public class dbutilities 
{
	static Connection connection =null;
	public static void initEmployeeDetails()
	{
		Employee empObj = new Employee();
		empObj.employeeID= "emp1@foods.com";
		empObj.emplyeeName = "Employee1";
		empObj.employeePrivilege = "DL";
		empObj.password="emp1";
		updateEmployeeDetails(empObj);
		
		empObj.employeeID= "emp2@foods.com";
		empObj.emplyeeName = "Employee2";
		empObj.employeePrivilege = "DA";
		empObj.password="emp2";
		updateEmployeeDetails(empObj);
		
		empObj.employeeID= "emp3@foods.com";
		empObj.emplyeeName = "Employee3";
		empObj.employeePrivilege = "SU";
		empObj.password="emp3";
		updateEmployeeDetails(empObj);
		
		empObj.employeeID= "emp4@foods.com";
		empObj.emplyeeName = "Employee4";
		empObj.employeePrivilege = "MR";
		empObj.password="emp4";
		updateEmployeeDetails(empObj);
		
		
		
		
	}
	public static void updateEmployeeDetails(Employee empObj){
		String insertIntoEmployeeQuery = "INSERT INTO employee VALUES "
				+ "(?, ?,?, ?);";
		try{
			PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoEmployeeQuery);
			pst.setString(1,empObj.employeeID);
			pst.setString(2,empObj.emplyeeName);
			pst.setString(3,empObj.employeePrivilege);
			pst.setString(4,empObj.password);
			pst.executeUpdate();
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Store> enterStoreListValues(){
		
		List<Store> storeList = new ArrayList<Store>();
		
		Store one_store = new Store();
		one_store.dealerName = "John";
		one_store.discount=10;
		String p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\cofffemate.jpg" ;
		
		one_store.icon_filename=p;
		one_store.idItem="item_001";
		one_store.inStock=true;
		one_store.isnew="y";
		one_store.itemDescription="Non Dairy Creame";
		one_store.itemName="cofffemate";
		one_store.price=(float) 4.99;
		one_store.selected=false;
		one_store.stock=8;
		
		

		Store two_store = new Store();
		two_store.dealerName = "Red";
		two_store.discount=20;
		p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\cookies.jpg" ;
		two_store.icon_filename=p;
		two_store.idItem="item_002";
		two_store.inStock=true;
		two_store.isnew="y";
		two_store.itemDescription="Homade Chocolate chip cookies";
		two_store.itemName="cookies";
		two_store.price=(float) 2.99;
		two_store.selected=false;
		two_store.stock=7;
		
		
		Store three_store = new Store();
		three_store.dealerName = "Snow";
		three_store.discount=10;
		p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\peanuts.jpg" ;
		three_store.icon_filename=p;
		three_store.idItem="item_003";
		three_store.inStock=true;
		three_store.isnew="y";
		three_store.itemDescription="Salted roasted peanuts";
		three_store.itemName="peanuts";
		three_store.price=(float) 2.99;
		three_store.selected=false;
		three_store.stock=10;
		
		
		Store four_store = new Store();
		four_store.dealerName = "Ned";
		four_store.discount=10;
		p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\oreo.jpg" ;
		four_store.icon_filename=p;
		four_store.idItem="item_004";
		four_store.inStock=false;
		four_store.isnew="y";
		four_store.itemDescription="Classic sandwich cookies";
		four_store.itemName="oreo";
		four_store.price=(float) 1.99;
		four_store.selected=true;
		four_store.stock=0;
		

		 Store five_store = new Store();
		 five_store.dealerName = "Kellogs";
		 five_store.discount=0;
	        p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\cornflakes.jpeg" ;
	        five_store.icon_filename=p;
	        five_store.idItem="item_005";
	        five_store.inStock=true;
	        five_store.isnew="y";
	        five_store.itemDescription="Delicious Fat Free Breakfast";
	        five_store.itemName="Kellogs Corn Flakes";
	        five_store.price=(float) 15.68;
	        five_store.selected=true;
	        five_store.stock=140;
	        
	        Store six_store = new Store();
	        six_store.dealerName = "Doritos";
	        six_store.discount=0;
	        p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\doritos.jpeg" ;
	        six_store.icon_filename=p;
	        six_store.idItem="item_006";
	        six_store.inStock=true;
	        six_store.isnew="y";
	        six_store.itemDescription="Cheese Falvoured Tortilla Chips";
	        six_store.itemName="Doritos";
	        six_store.price=(float) 3.68;
	        six_store.selected=true;
	        six_store.stock=66;
	        
	        Store seven_store = new Store();
	        seven_store.dealerName = "Lipton";
	        seven_store.discount=0;
	        p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\liptonIceTea.jpeg" ;
	        seven_store.icon_filename=p;
	        seven_store.idItem="item_007";
	        seven_store.inStock=true;
	        seven_store.isnew="y";
	        seven_store.itemDescription="Real Tea Leaves";
	        seven_store.itemName="Lipton Iced Tea";
	        seven_store.price=(float) 5.99;
	        seven_store.selected=true;
	        seven_store.stock=17;
	        
	        Store eight_store = new Store();
	        eight_store.dealerName = "Quaker";
	        eight_store.discount=0;
	        p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\oats.jpeg" ;
	        eight_store.icon_filename=p;
	        eight_store.idItem="item_008";
	        eight_store.inStock=true;
	        eight_store.isnew="y";
	        eight_store.itemDescription="Natural Whole Grain";
	        eight_store.itemName="Quaker Oats";
	        eight_store.price=(float) 4.08;
	        eight_store.selected=true;
	        eight_store.stock=5;
	        
	        Store nine_store = new Store();
	        nine_store.dealerName = "Heinz";
	        nine_store.discount=0;
	        p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\Heinz_TomatoKetchup.jpeg" ;
	        nine_store.icon_filename=p;
	        nine_store.idItem="item_009";
	        nine_store.inStock=true;
	        nine_store.isnew="y";
	        nine_store.itemDescription="Gluten Free";
	        nine_store.itemName="Heinz TomatoKetchup";
	        nine_store.price=(float) 2.88;
	        nine_store.selected=true;
	        nine_store.stock=10;
	        
	        Store ten_store = new Store();
	        ten_store.dealerName = "Ritz";
	        ten_store.discount=0;
	        p = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\src\\eStore\\ritzcrackers.jpeg" ;
	        ten_store.icon_filename=p;
	        ten_store.idItem="item_010";
	        ten_store.inStock=true;
	        ten_store.isnew="y";
	        ten_store.itemDescription="Filling made with real cheese";
	        ten_store.itemName="Ritz Crackers";
	        ten_store.price=(float) 3.68;
	        ten_store.selected=true;
	        ten_store.stock=15;
	        
	        storeList.add(one_store);
	        storeList.add(two_store);
	        storeList.add(three_store);
	        storeList.add(four_store);
	        storeList.add(five_store);
	        storeList.add(six_store);
	        storeList.add(seven_store);
	        storeList.add(eight_store);
	        storeList.add(nine_store);
	        storeList.add(ten_store);

		
		
		for(Store stores : storeList){
			updateStoreListDetails(stores);
		}
		
		
		return null;
		
	}
	
	public int updateCustomerDetails(Customer cobj) 
	{

		String insertIntoCustomerReservationQuery = "INSERT INTO account VALUES "
				+ "(?, ?,?, ?,?, ?,?, ?,?,?, ?);";
		try {
			PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoCustomerReservationQuery);
			pst.setString(1,cobj.custID);
			pst.setString(2,cobj.custFName);
			pst.setString(3,cobj.custLName);
			pst.setString(4,cobj.custAddress);
			pst.setString(5,cobj.custState);
			pst.setInt(6,cobj.custZip);
			pst.setString(7,cobj.nameonCard);
			pst.setDouble(8,cobj.cardNumber);
			pst.setInt(9, cobj.cvv);
			pst.setString(10,cobj.exp);
			pst.setString(11,cobj.custPswd);
			
			pst.executeUpdate();
			return 1;
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
			return 2;
		
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void updateStoreListDetails(Store storeObj){
		String insertIntoEmployeeQuery = "INSERT INTO storelist VALUES "
				+ "(?,?,?,?,?,?,?,?,?);";
		try{
			PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoEmployeeQuery);
			pst.setString(1,storeObj.idItem);
			pst.setString(2,storeObj.itemName);
			pst.setString(3,storeObj.itemDescription);
			pst.setInt(4,storeObj.stock);
			pst.setFloat(5,storeObj.price);
			pst.setFloat(6,storeObj.discount);
			pst.setString(7,storeObj.dealerName);
			pst.setString(8,storeObj.isnew);
			File filename = new File(""+storeObj.icon_filename);
			try {
				FileInputStream fis = new FileInputStream(filename);
				pst.setBinaryStream(9,fis, (int)(filename.length()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pst.executeUpdate();
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public static ArrayList<Store> getStoreList()
	{
		
		 
		try {
			String query="select itemName,itemDescription,icon,stock,price,discount from storelist";
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			ArrayList<Store> data = new ArrayList<Store>();
		
			
				while(rs.next())
				{
					Store p = new Store();
					p.itemName = rs.getString("itemName"); 
					p.itemDescription = rs.getString("itemDescription");
					p.icon = rs.getBytes("icon");
					p.stock = rs.getInt("stock");
					p.price = rs.getInt("price");
					p.discount = rs.getFloat("discount");
					p.selected = false; 
					p.selectedQty = 1;
					data.add(p);
				
					byte[] img = rs.getBytes("icon");
					ImageIcon image = new ImageIcon(img); 
					
				}
			
				return data; 
			}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
		
	}
	public static ResultSet dealerdata()
	{
		ResultSet rs = null;
		try {
			String query="select itemID,itemName,stock,price,discount from storelist";
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			rs=pst.executeQuery();
			
			return rs;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return rs;
		}
		
	}
	public static String getPrivilege(String id)
	{
		try {
			String query="select employeePrivilege from Employee where employeeID="+"'"+id+"'";
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				return rs.getString("employeePrivilege");
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	public static String validatepwd(String id,String table)
	{
		 
		try {
			String query;
			if(table.equals("Employee"))
			{
				query ="select password from " + table +" where employeeID="+"'"+id+"'";
			}
			else
			{
				query ="select password from " + table +" where customerID="+"'"+id+"'";
			}
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				return rs.getString("password");
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}
	


	public static Connection getDafaultConnection() 
	{
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	        
	     
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","goutham911");
        //   System.out.println("\n Driver is connected Successfully");
   
		}
		catch (SQLException ex) {
	    
    	  	System.out.println("SQLException: " + ex.getMessage());
    	    System.out.println("SQLState: " + ex.getSQLState());
    	    System.out.println("VendorError: " + ex.getErrorCode());

    	}
		catch (Exception ex) 
		{
			ex.printStackTrace();
    	
		}
		 return connection;
		
	}
	
	public static void updateOrderDelivery(String order_id){
		
		PreparedStatement update;
		try {
			update = MakeConnection.getConnection("FOODSTORE").prepareStatement
				    ("UPDATE orders SET deliveryStatus = ?, delivery = ? WHERE idOrders = ?");
			update.setString(1, "1");
			update.setString(2, "delivered");
			update.setString(3, order_id);

			update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
			
	}
	
public static void updateNonOrderDelivery(String order_id){
		
		PreparedStatement update;
		try {
			update = MakeConnection.getConnection("FOODSTORE").prepareStatement
				    ("UPDATE orders SET deliveryStatus = ?, delivery = ? WHERE idOrders = ?");
			update.setString(1, "0");
			update.setString(2, "not delivered");
			update.setString(3, order_id);

			update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
			
	}
	
	
	public static ArrayList<Orders> getOrders()
	{
		
		 
		try {
			
			String query="select idOrders,idCustomer,idProduct,shippingAddress,deliveryStatus,delivery from orders";
			PreparedStatement pst = MakeConnection.getConnection("FOODSTORE").prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			ArrayList<Orders> data = new ArrayList<Orders>();
		
			
				while(rs.next())
				{
					Orders p = new Orders();
					//p.idOrders = rs.getString("idOrders"); 
					p.idCustomer = rs.getString("idCustomer");
					p.idProduct = rs.getString("idProduct");
					p.shippingAddress = rs.getString("shippingAddress");
					p.deliveryStatus = rs.getBoolean("deliveryStatus");
					p.delivery = rs.getString("delivery");
					data.add(p);
					
				}
			
				return data; 
			}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
		
	}
	public static ArrayList<SupportTicket> getSupportTicketList(String user)
	{
		try
		{
			String query="select ticketID,ticketDescription,ticketReporter from ticket where ticketAssignee="+"'"+user+"'";
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			ArrayList<SupportTicket> res = new ArrayList<SupportTicket>();
		
			
				while(rs.next())
				{
					SupportTicket n=new SupportTicket();
					n.supportTicketID=rs.getInt("ticketID");
					n.problemDetails=rs.getString("ticketDescription");
					n.reporter=rs.getString("ticketReporter");
					res.add(n);
				}
				return res;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}
	
	public static ArrayList<SupportTicket> getSupportTicketList()
	{
		try
		{
			String query="select ticketID,ticketDescription,ticketReporter from ticket where ticketStatus='Open'";
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			ArrayList<SupportTicket> res = new ArrayList<SupportTicket>();
		
			
				while(rs.next())
				{
					SupportTicket n=new SupportTicket();
					n.supportTicketID=rs.getInt("ticketID");
					n.problemDetails=rs.getString("ticketDescription");
					n.reporter=rs.getString("ticketReporter");
					res.add(n);
				}
				return res;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
		
	}
	public static void updateSupportListDetails(SupportTicket st)
	{
		String insertIntoSupportQuery = "UPDATE ticket SET ticketSolution="+"'"+st.proposedSolution+"' ,ticketStatus="+"'"+st.status+"'"+
										 "where ticketID="+"'"+st.supportTicketID+"'" ;
		try{
			PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoSupportQuery);
		
			pst.executeUpdate();
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void createSupportTicket(String reporter,String query)
	{
		String insertIntoSupportQuery = "insert into ticket (ticketReporter, ticketDescription, ticketStatus)"
		        + " values (?, ?, ?)";
		
		try{
		PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoSupportQuery);
		pst.setString(1, reporter);
		pst.setString(2, query);
		pst.setString(3, "Open");
		
		pst.executeUpdate();
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
		e.printStackTrace();
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public static List<String>  getEmployees(){
		 List<String> employees = new ArrayList<String>();
		 try {
				String query="select employeeID from employee ";
				PreparedStatement pst = DbInit.conn.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
					employees.add(rs.getString("employeeID"));
				
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		 
		 return employees; 
	}
	public static String getAddress(String user) {
		try {
			String query="select customerAddress, addressState,zip  from account where customerID="+"'"+user+"'";
			PreparedStatement pst = DbInit.conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				return (rs.getString("customeraddress")+'\n'+rs.getString("addressState")+'\n'+rs.getString("zip")); 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	public static void setOrders(Orders orders) {
		// TODO Auto-generated method stub
		String insertIntoSupportQuery = "insert into orders "
		        + " values (?, ?,?, ?,?,?)";
		
		try{
			PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoSupportQuery);
			//pst.setString(1, orders.idOrders);
			pst.setString(2, orders.idCustomer);
			pst.setString(3, orders.idProduct);
			pst.setString(4, orders.shippingAddress);
			
			pst.setString(5, null);
			pst.setString(6, "Standard");
		
			pst.executeUpdate();
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
		e.printStackTrace();
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
	}
	public static void setAssignee(String string, int supportTicketID) {

		String insertIntoSupportQuery = "UPDATE ticket SET ticketAssignee="+"'"+string+"'"+
										 "where ticketID="+"'"+supportTicketID+"'" ;
		try{
			PreparedStatement pst = DbInit.conn.prepareStatement(insertIntoSupportQuery);
		
			pst.executeUpdate();
		}
		catch (SQLIntegrityConstraintViolationException  e) 
		{	
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	
	
	
	
			
				
}