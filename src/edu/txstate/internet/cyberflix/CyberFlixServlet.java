package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.utils.ServletUtils;
import edu.txstate.internet.cyberflix.utils.HTMLTags;

/**
 * Servlet implementation class CyberFlixServlet
 */
@WebServlet("/CyberFlixServlet")
public class CyberFlixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CyberFlixServlet() {
		super();

	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ServletUtils.setAbsolutePath(config);
		DataSource.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		String filmTitle = request.getParameter("film_title");
		
		request.setAttribute("detailServlet", "http://localhost:8080/CyberFlixOne/CyberFlixMovieDetailServlet");
		
		if (filmTitle != null) {
			List<Film> foundFilms = DataSource.findFilmByTitle(filmTitle);
			if ((foundFilms != null) && (!foundFilms.isEmpty())) {
					request.setAttribute("films",  foundFilms);
					request.getRequestDispatcher("MovieSearchResults.jsp").forward(request, response);
			} else {
				writer.append("<p>Sorry! Your search didn't return anything. Please try again");
				String backPath = ServletUtils.getLocalHost() + ServletUtils.getProjectName()
						+ "/index.html";
				writer.append(createLink(backPath, "Go Back"));

			}
		}
	}

	private String createLink(String target, String text) {
		final String ANCHOR_TAG_START = "<a href=" + "\"";
		final String ANCHOR_TAG_CLOSE = "\"" + ">";
		final String ANCHOR_TAG_END = "</a>";

		StringBuilder stringBuilder = new StringBuilder(ANCHOR_TAG_START);
		stringBuilder.append(target);
		stringBuilder.append(ANCHOR_TAG_CLOSE);
		stringBuilder.append(text);
		stringBuilder.append(ANCHOR_TAG_END);
		return stringBuilder.toString();
	}
}
