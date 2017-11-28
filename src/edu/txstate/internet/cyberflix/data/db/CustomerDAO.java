package edu.txstate.internet.cyberflix.data.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends DAO{
	
	private static final String CUSTOMER_SELECT_STRING = "SELECT customer_id, first_name, last_name, email, password FROM customer WHERE email = ?";
	
	public Customer findCustomerWithEmail(String emailAddress){
		try {
				java.sql.PreparedStatement statement = Driver.conn.prepareStatement(CUSTOMER_SELECT_STRING);
				statement.setString(1, emailAddress);
				
				ResultSet result = statement.executeQuery(statement.toString());
				
				int id;
				String firstName, lastName, email, password;
				if(result != null)
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
