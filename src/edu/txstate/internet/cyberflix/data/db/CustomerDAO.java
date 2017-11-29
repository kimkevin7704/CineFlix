package edu.txstate.internet.cyberflix.data.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class CustomerDAO extends DAO{
	
	public static Customer findCustomerWithEmail(String emailAddress){
		try {
			  PreparedStatement st = null;
			  Connection cn = null;
			  cn = DAO.getDBConnection();
			  st = cn.prepareStatement("select * from customer where email=?");
			  st.setString(1, emailAddress);
			  
			  ResultSet result = st.executeQuery();
			
			//java.sql.PreparedStatement statement = Driver.conn.prepareStatement();
			//statement.setString(1, emailAddress);
				
			//ResultSet result = statement.executeQuery(statement.toString());
				
				int id;
				String firstName, lastName, email, password;
				while(result.next())
				{
					id = result.getInt("customer.customer_id");
					firstName = result.getString("customer.first_name");
					lastName = result.getString("customer.last_name");
					email = result.getString("customer.email");
					password = result.getString("customer.password");
					
					Customer customer = new Customer(id, firstName, lastName, email, password);
					return customer;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		Customer customer = new Customer();
		return customer;
	}

	
	@Override
	public void save(Object anObject) throws SQLException {
	}
}
