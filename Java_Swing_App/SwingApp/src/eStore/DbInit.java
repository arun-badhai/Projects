package eStore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import eStore.MakeConnection;

public class DbInit {
	static Connection conn = null;
	DbInit(){
		initialize();
	}
	
	public void initialize(){
		conn = MakeConnection.getDafaultConnection();
		if (conn != null) {
		
			createTablespace(conn);
			conn = MakeConnection.getConnection("FOODSTORE");
			createTables(conn);
			dbutilities.enterStoreListValues();
			dbutilities.initEmployeeDetails();

		
		}
		
	}
	
	public void createTablespace(Connection conn) {

		String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS FOODSTORE";
		String useDB = "USE FOODSTORE";
		//String createTablespaceCommand1 = "DROP TABLESPACE IF EXISTS FOODSTORE ;";
		//String createTablespaceCommand2 = " CREATE TABLESPACE FOODSTORE ADD DATAFILE 'FOODSTORE.ibd';";
		Statement statement = null;
		try {
			statement = conn.createStatement();
			statement.executeUpdate(createDatabaseQuery);
			statement.executeUpdate(useDB);
			//statement.executeUpdate(createTablespaceCommand1);
			//statement.executeUpdate(createTablespaceCommand2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	public void createTables(Connection conn) {
		Statement statement = null;
		try {
			statement = conn.createStatement();
					
	
		
		String createTableAccountQuery2 = 
		"CREATE TABLE IF NOT EXISTS account ("
		+ "customerID varchar(20) NOT NULL,"
		+"customerFName varchar(20) NOT NULL,customerLName varchar(20) DEFAULT NULL, customerAddress varchar(200) NOT NULL,"
		+"addressState varchar(45) NOT NULL,"
		+"zip int(11) NOT NULL, nameonCard varchar(45) NOT NULL,"
		+ "cardNumber BIGINT(20) NOT NULL,"
		+"CVV int(11) NOT NULL,"
		+"expiryDate varchar(5) NOT NULL,"
		+"password varchar(45) NOT NULL,"
		+"PRIMARY KEY (customerID),"
		+"UNIQUE KEY customeID_UNIQUE (customerID))";
		
		statement.executeUpdate(createTableAccountQuery2);
			
			
			//String createTableOrdersQuery1 = "DROP TABLE IF EXISTS orders;";
			//statement.executeUpdate(createTableOrdersQuery1);
			
			String createTableOrdersQuery2 = 
								"CREATE TABLE IF NOT EXISTS orders ("
			+"idOrders int NOT NULL AUTO_INCREMENT,"
			  +"idCustomer varchar(45) NOT NULL,"
			  +"idproduct varchar(45) NOT NULL,"
			  +"shippingAddress varchar(500) NOT NULL,"
			  +"deliveryStatus TINYINT,"
			  +"delivery varchar(500) NOT NULL,"
			  +"PRIMARY KEY (idOrders),"
			  +"UNIQUE KEY idOrders_UNIQUE (idOrders)"
			  +");";
			
			statement.executeUpdate(createTableOrdersQuery2);
			
			//String createTableStoreListQuery1 = "DROP TABLE IF EXISTS storelist;";
			//statement.executeUpdate(createTableStoreListQuery1);
			
			String createTableStoreListQuery2 = 
					"CREATE TABLE IF NOT EXISTS storelist ("
								  +"itemID varchar(20) NOT NULL,"
								  +"itemName varchar(50) NOT NULL,"
								  +"itemDescription varchar(500) DEFAULT NULL,"
								  +"stock int(11) NOT NULL,"
								  +"price float NOT NULL,"
								  +"discount int(11) DEFAULT NULL,"
								  +"dealerName varchar(20) NOT NULL,"
								  +"isnew varchar(1) NOT NULL,"
								  +"icon LONGBLOB,"  
								  +"PRIMARY KEY (itemID,itemName),"
								  +"UNIQUE KEY itemID_UNIQUE (itemID),"
								  +"UNIQUE KEY itemName_UNIQUE (itemName)"
								  + ");";
		

			statement.executeUpdate(createTableStoreListQuery2);
			
			/*String createTableEmployeeQuery1 = "DROP TABLE IF EXISTS employee;";
			statement.executeUpdate(createTableEmployeeQuery1);*/	
			
			String createTableEmployeeQuery2 = 
					"CREATE TABLE IF NOT EXISTS employee ("
								  +"employeeID varchar(20) NOT NULL,"
								  +"employeeName varchar(50) NOT NULL,"
								  +"employeePrivilege varchar(100) NOT NULL,"
								  +"password varchar(20) NOT NULL,"
								  +"PRIMARY KEY (employeeID),"
								  +"UNIQUE KEY employeeID_UNIQUE (employeeID)"
								  + ");";


			statement.executeUpdate(createTableEmployeeQuery2);		

			//String createTableTicketQuery1 = "DROP TABLE IF EXISTS ticket;";
			//statement.executeUpdate(createTableTicketQuery1);
			
			String createTableTicketQuery2 = 
					"CREATE TABLE IF NOT EXISTS ticket ("
								+"ticketID int NOT NULL AUTO_INCREMENT,"
								+"ticketAssignee varchar(50) NOT NULL DEFAULT 'default',"
								+"ticketReporter varchar(50) NOT NULL,"
								+"ticketDescription varchar(500) NOT NULL,"
								+"ticketStatus varchar(20) NOT NULL,"
								+"ticketSolution varchar(500),"
								+"PRIMARY KEY (ticketID),"
								+"UNIQUE KEY ticketID_UNIQUE (ticketID)"
								+ ");";
								
								
								statement.executeUpdate(createTableTicketQuery2);



		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	
	
}
