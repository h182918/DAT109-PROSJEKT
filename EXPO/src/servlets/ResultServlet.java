package servlets;

import entities.Stand;
import db.DbHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The purpose of the Result Servlet is to find the total score for the
 * spesified stand and forward the result to result.jsp
 * 
 * It is the last link of the current webapp and has only got the doGet() method implemented
 *
 */

@WebServlet(name = "ResultServlet", urlPatterns = "/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DbHandler db;

	/**
	 * Creates one DbHandler for all to use.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		db = new DbHandler();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Not needed, but can be used to fx. find old votes for the user and thereby create a loop for the webapp. 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String standIdstr = request.getParameter("standId");
		int standId = Integer.parseInt(standIdstr);
		Stand stand = db.getStand(standId);

		double avg = db.findAverageVote(standId);

		String vote = request.getParameter("vote");

		request.setAttribute("avg", avg);
		request.setAttribute("stand", stand);
		request.setAttribute("vote", vote);
		request.getRequestDispatcher("WEB-INF/jsp/result.jsp").forward(request, response);
	}
}
