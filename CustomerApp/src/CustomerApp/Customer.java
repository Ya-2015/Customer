package CustomerApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Customer {

	
		public void getCustomerInfo(int id,String url,Properties props) throws SQLException{
			try{
			//creating connection to Oracle database using JDBC
	        Connection conn = DriverManager.getConnection(url,props);
	        
	        //*********************//
	        String sql ="select customer.customer_id,customer.title,customer.firstname,customer.lastname,customer.STREETADDRESS,city_state.city,\n"+
	        "city_state.state as mystate,customer.zipcode,customer.emailaddress,customer.position,company.company from customer inner join company on \n"+
	        "customer.company_id = company.company_id inner join city_state on city_state.city_state_id=customer.city_state_id where customer.customer_id = ?";
	        
//	        String sql = "select * from city_state";

	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        preStatement.setInt(1, id);
	        ResultSet result = preStatement.executeQuery();
	      
	        while(result.next()){
	            System.out.print("Customer Number: " + result.getString(1)+"\n");
	            System.out.print(result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+"\n");
	            System.out.print(result.getString(5)+"\n");
	            System.out.print(result.getString(6) +", "+ result.getString(7)+ " "+ result.getString(8)+"\n");
	            System.out.print(result.getString(9)+"\n");
	            System.out.print(result.getString(10)+ " at "+ result.getString(11)+"\n");
	        }
	        
	        conn.close();
	        }catch(Exception e){
	        	System.out.println(e.getMessage());
	        }
			
		}

		//String sql ="update customer set streetaddress='" +address + "' where customer_id=" +id;
		public boolean updateCustomerStreetAddress(int id,String address,String url,Properties props) throws SQLException{
	        String sql ="update customer set streetaddress = ? where customer_id = ?";
	        boolean isSuccess = false;
//	        return updateDatabase(sql, url, props);
	        try{
	        
			//creating connection to Oracle database using JDBC
	        Connection conn = DriverManager.getConnection(url,props);
	        
	        //System.out.println(sql);
	        //creating PreparedStatement object to execute query
	        PreparedStatement preStatement = conn.prepareStatement(sql);
	        preStatement.setString(1, address);
	        preStatement.setInt(2, id);
	        int result = preStatement.executeUpdate();
	        conn.close();
	        
	        if (result != 0){
	        	isSuccess = true;
	        }
	        }catch(Exception e){
	        	isSuccess = false;
	        }
	        return isSuccess;
		}
		
		public boolean updateCustomerZipcode(int id,int zipcode,String url,Properties props) throws SQLException{
	        String sql ="update customer set zipcode = ? where customer_id = ?";
	        
	        boolean isSuccess = false;
	        
//	        System.out.println(sql);
	        //creating PreparedStatement object to execute query
	        try
	        {
	        	//creating connection to Oracle database using JDBC
		        Connection conn = DriverManager.getConnection(url,props);
		        PreparedStatement preStatement = conn.prepareStatement(sql);
		        preStatement.setInt(1, zipcode);
		        preStatement.setInt(2, id);
		        
		        int result = preStatement.executeUpdate();
		        conn.close();
		        
		        if (result != 0){
		        	isSuccess = true;
		        }
	        }catch(Exception e){
	        	isSuccess = false;
	        }
	        
	        return isSuccess;
		}
		
	public boolean updateCityState(int id, String city, String state, String url,Properties props) throws SQLException{
		String sql ="select city_state_id from city_state where city=? and state = ?";
		boolean isSuccess = false;
		try{
		Connection conn = DriverManager.getConnection(url,props);
		
		PreparedStatement preStatement = conn.prepareStatement(sql);
		
		preStatement.setString(1, city);
		preStatement.setString(2, state);
	    
        ResultSet result = preStatement.executeQuery();
        int city_state_id = -1;
        if(result.next()){
        	city_state_id = result.getInt(1);
        }
        
		if(city_state_id != -1){
			//update customer table
			sql ="update customer set city_state_id = ? where customer_id = ?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, city_state_id);
			preStatement.setInt(2, id);
			preStatement.executeUpdate();
			isSuccess = true;
		}else{
			//update city_state table
			sql ="insert into city_state(city, state, city_state_id) values (?, ?, (select count(*) from city_state)+1)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, city);
			preStatement.setString(2, state);
			preStatement.executeUpdate();
			
			//update customer table
			sql ="select city_state_id from city_state where city=? and state = ?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, city);
			preStatement.setString(2, state);
			result = preStatement.executeQuery();
	        city_state_id = -1;
	        if(result.next()){
	        	city_state_id = result.getInt(1);
	        	sql ="update customer set city_state_id = ? where customer_id = ?";
	        	preStatement = conn.prepareStatement(sql);
				preStatement.setInt(1, city_state_id);
				preStatement.setInt(2, id);
				preStatement.executeUpdate();
				isSuccess = true;
	        }
			
			
		}
        
        conn.close();
		}
		catch (Exception e){
			isSuccess = false;
		}
        
        return isSuccess;
		
	}

}
