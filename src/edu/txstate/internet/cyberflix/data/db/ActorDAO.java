package edu.txstate.internet.cyberflix.data.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.film.Film;

public class ActorDAO extends DAO{
	
	private static final String ACTOR_FIND_STRING = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id IN (SELECT actor_id FROM film_actor WHERE film_id = ?)";
	
	public List <Actor> getActorsInFilm(Film film)
	{
		List <Actor> actors = new ArrayList <Actor>();
		
		String filmID = Integer.toString(film.getFilmID());
		//get all actor_id from film_actor by checking with film_id
		
		try {
			String statementString = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id IN (SELECT actor_id FROM film_actor WHERE film_id = " + filmID + ")";
			java.sql.PreparedStatement getActorsStatement = Driver.conn.prepareStatement(ACTOR_FIND_STRING);
			ResultSet result = getActorsStatement.executeQuery(statementString);
			
			while(result.next())
			{
				int c = 0;

				String firstName, lastName;
				int actorID;
				
				firstName = result.getString("actor.first_name");
				lastName = result.getString("actor.last_name");
				actorID = result.getInt("actor.actor_id");
				
				Actor actor = new Actor(actorID, firstName, lastName);
				actors.add(c, actor);
				c++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}
	
	@Override
	public void save(Object anObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
