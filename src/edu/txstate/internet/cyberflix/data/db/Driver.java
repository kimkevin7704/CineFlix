package edu.txstate.internet.cyberflix.data.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.*;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import com.mysql.jdbc.PreparedStatement;

public class Driver {

	public static Connection conn;
	
	public static void main(String[] args) {
		try {
			conn = null;
			try {
				conn = DAO.getDBConnection();
				FilmDAO filmDAO = new FilmDAO();
				if(conn != null)
				{
					System.out.println("connected successfully to database");
				}
				
				//change movie dates to 2017
				//updateMovieYears();
				//populate phone number column with random 10 digit numbers
				//populatePhoneNums();
				//populate password column with random 20 char passwords
				//populatePasswords();
				System.out.println("updated movie years, phone numbers, passwords added");
				
				//display 8 most recent films
				int maxDisplay = 8;
				List<Film> newestFilms = new ArrayList<Film>();
				newestFilms = filmDAO.findNewestFilms(maxDisplay);
				displayNewFilms(newestFilms, maxDisplay);
				
				//browse by attribute
				Film.FilmRating filmRating = Film.FilmRating.R;
				List<Film> filmsByAttribute = new ArrayList<Film>();
				filmsByAttribute = filmDAO.findFilmsByAttributes("agent", "drama", 70, filmRating);
				System.out.println("top 8 results from attribute search:\n\n");
				displayResults(filmsByAttribute, maxDisplay);
				
				//browse by category with test example "COMEDY"
//				FilmCategory sampleCategory = FilmCategory.COMEDY;
//				List<Film> filmsByCategory = new ArrayList<Film>();
//				filmsByCategory = filmDAO.findFilmsByCategory(sampleCategory);
//				System.out.println("top 8 results from category search:\n\n");
//				displayResults(filmsByCategory, maxDisplay);
//				System.out.println("got to end of category");
				
				//browse alphabetically
//				List<Film> filmsByAlphabet = new ArrayList<Film>();
//				System.out.println("before alphabet search");
//				filmsByAlphabet = filmDAO.findFilmsAlphabetically("f");
//				System.out.println("after search");
//				System.out.println("first 8 movies starting with F:\n\n");
//				displayResults(filmsByAlphabet, maxDisplay);
				
				
				
				
				
			
			} catch (Exception e) {
				System.out.println("not connected to database");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateMovieYears() {
		int[] newFilms = {1, 50, 111, 206, 271, 299, 438, 494};
		for(int i = 0; i < newFilms.length; i++)
		{
			try {
				Statement statement = conn.createStatement();
				String statementString = "update film " + " set release_year='2017'" + " where film_id = " + newFilms[i];
				statement.executeUpdate(statementString);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void populatePhoneNums() {
		
		for(int i = 0; i < 600; i++)
		{
			Random generator = new Random();
			
			int dig1, dig2, dig3, group1, group2;
			String phoneNumber = null;
			
			dig1 = generator.nextInt(7) + 1;
			dig2 = generator.nextInt(8);
			dig3 = generator.nextInt(8);
			group1 = generator.nextInt(899) + 100;
			group2 = generator.nextInt(8999) + 1000;
			
			phoneNumber = Integer.toString(dig1) + Integer.toString(dig2) + Integer.toString(dig3) + Integer.toString(group1) + Integer.toString(group2);
			
			try {
				Statement statement = conn.createStatement();
				String statementString = "update customer " + " set phone_number='" + phoneNumber + "' where customer_id = " + Integer.toString(i);
				statement.executeUpdate(statementString);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		System.out.println("phone numbers added");
	}
	
	public static void populatePasswords(){
		String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		
		for(int i = 0; i < 600; i++)
		{
			Random generator = new Random();
			String password = "";
			
			//get random character
			int passwordLengthCount = 0;
			while(passwordLengthCount <= 19)
			{
				char randomChar;
				String randomCharString;
				randomChar = validChars.charAt(generator.nextInt(validChars.length()));
				randomCharString = Character.toString(randomChar);
				password = password + randomCharString;
				passwordLengthCount++;
			}
			
			try {
				Statement passStatement = conn.createStatement();
				String statementString = "update customer " + " set password='" + password + "' where customer_id = " + Integer.toString(i);
				passStatement.executeUpdate(statementString);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void displayNewFilms(List<Film> newFilms, int maxDisplay) {
		System.out.println("\n\nthe top 5 newest films are: \n");	
		for(int i = 0; i < maxDisplay; i++)
		{
			Film film = newFilms.get(i);
			String filmString = film.toString();
			System.out.println(i+1 + ". " + filmString);
		}
		System.out.println("\n\n");
	}
	
	public static void displayResults(List<Film> filmList, int maxDisplay) {	
		for(int i = 0; i < maxDisplay; i++)
		{
			Film film = filmList.get(i);
			String filmString = film.toString();
			System.out.println(i+1 + ". " + filmString);
		}
		System.out.println("\n\n");
	}
}
