package CustomerApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class CustomerAPP {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
//		System.out.println("CustomerID?");
		int customerId = 0, press=0;
		
        Customer customer= new Customer();
        
        //URL of Oracle database server
        String url = "jdbc:oracle:thin:system/password@localhost"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testuserdb");
        props.setProperty("password", "password");
      
//        customer.getCustomerInfo(customerId, url, props);
//        System.out.println("Press (1) to search for another customer or press (2) to Edit the customer's address.");
//        press = scan.nextInt();

        press = 1;
        String streetAddress="";
        int zipcode = 0;
        do{
        	if (press == 1){
        		System.out.print("CustomerID? ");
            	customerId =scan.nextInt();
            	customer.getCustomerInfo(customerId, url, props);
        	}else if (press == 2){
        		//street address
        		System.out.println("Please enter a new address. Or please press 0 if no update needed. ");
//        		streetAddress=scan.nextLine();
        		streetAddress=scan.nextLine();
        		if(!streetAddress.equals("0")){
        			if(customer.updateCustomerStreetAddress(customerId, streetAddress, url, props)){
        				System.out.print("Address updated: " + streetAddress+"\n");
        			}else{
        				System.out.print("Address not updated"+"\n");
        			}
        		}
        		
        		//zipcode
        		System.out.print("Please enter a zipcode. Or please press 0 if no update needed. ");
        		String str = scan.next();
        		if(!str.equals("0")){
        			zipcode=Integer.parseInt(str);
        			if(customer.updateCustomerZipcode(customerId, zipcode, url, props)){
        				System.out.print("Zipcode updated: " + zipcode+"\n");
        			}else{
        				System.out.print("Zipcode not updated"+"\n");
        			}
        		}
        		
        		//city and state
        		System.out.print("Please enter a city. Or please press 0 if no update needed. ");
        		String city = scan.next();
        		if(!city.equals("0")){
        			System.out.print("Please enter a state. Or please press 0 if no update needed. ");
        			String state = scan.next();
        			if(state.equals("0")){
        				break;
        			}
        			if(customer.updateCityState(customerId, city, state, url, props)){
        				System.out.print("City and State updated: " + city +" " +state+"\n");
        			}else{
        				System.out.print("City and State not updated: " + city + state+"\n");
        			}
        		}
        		
        		
        	}
        
        	System.out.print("Press (1) to search for another customer or press (2) to Edit the customer's address or press(0) to exit. ");
            press = scan.nextInt();
        }while(press!=0);
        
	}

}

