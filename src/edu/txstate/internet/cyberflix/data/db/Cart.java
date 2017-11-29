package edu.txstate.internet.cyberflix.data.db;

import java.util.ArrayList;
import java.util.List;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCatalog;

public class Cart {
	private static ArrayList <Film> cart = new ArrayList <Film>();
	private static Cart instance = null;
	
	public static Cart getInstance () {
		if (instance == null) {
			instance = new Cart ();
		}
		return instance;
	}

	public static void addMovieToCart(Film cartFilm) {
		cart.add(cartFilm);
	}
	
	public static void showMoviesInCart() {
		for(int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getTitle());
		}
	}
}
