package CustomerApp;
import org.junit.*;


import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class CustomerTesting {

	@Test
	public void testGetCustomerInfomationFalse() throws SQLException {
		Customer customer= new Customer();
        
        //URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        boolean result = customer.updateCustomerStreetAddress(5000, "", url, props);
        assertFalse(result==true);
        
        
        
	}
	
	@Test
	public void testGetCustomerInfomationTrue() throws SQLException {
		Customer customer= new Customer();
        
        //URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        boolean result = customer.updateCustomerStreetAddress(3000, "", url, props);
        assertTrue(result==true);
        
        
        
	}
	@Test
	public void testUpdateZipcodeInfomationTrue() throws SQLException {
		Customer customer= new Customer();
        
        //URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        boolean result = customer.updateCustomerZipcode(9, 11111, url, props);
        assertTrue(result==true);
        
        
        
	}

	@Test
	public void testUpdateZipcodeInfomationFalse() throws SQLException {
		Customer customer= new Customer();
        
        //URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
        
        boolean result = customer.updateCustomerZipcode(9, 1234988, url, props);
      
        assertFalse(result==true);
        
	}

}
