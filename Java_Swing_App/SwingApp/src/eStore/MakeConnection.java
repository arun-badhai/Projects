package eStore;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MakeConnection {
	public static String user;
	public static String pwd;
		@SuppressWarnings("unused")
		public static void Connection() throws IOException {
			
				Properties prop = new Properties();
				String propFileName = Paths.get(".").toAbsolutePath().normalize().toString()+"\\config.properties";
				
	 
				InputStream  inputStream=  new FileInputStream("config.properties"); 
	 
				if (inputStream != null) {
					prop.load(inputStream);
				} 
				else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
				user = prop.getProperty("user");
				pwd = prop.getProperty("password");
		
	}
		
		
        public static Connection getDafaultConnection() {
                try {
                		Connection();
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        Connection conn = null;

                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pwd);
                        System.out.println("\n Driver is connected Successfully");

                        return conn;
                } catch (SQLException ex) {
                        // handle any errors
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                        return null;
                } catch (Exception ex) {
                        // handle the error
                        ex.printStackTrace();
                        return null;
                }
        }
        public static Connection getConnection(String databaseName) {
                try {
                		Connection();
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        Connection conn = null;

                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, pwd);
                        System.out.println("\n Driver is connected Successfully");

                        return conn;
                } catch (SQLException ex) {
                        // handle any errors
                        System.out.println("SQLException: " + ex.getMessage());
                        System.out.println("SQLState: " + ex.getSQLState());
                        System.out.println("VendorError: " + ex.getErrorCode());
                        return null;
                } catch (Exception ex) {
                        // handle the error
                        ex.printStackTrace();
                        return null;
                }
        }

}