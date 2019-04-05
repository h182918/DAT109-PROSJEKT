package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.DbHandler;
import entities.Stand;
import entities.Vote;
import login.LoginUtil;

import java.io.IOException;

@WebServlet(name = "StandServlet", urlPatterns = "/Stand")
public class StandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doPost(..) handles the users vote for the stand. It receives the parameters
	 * for the vote and stores the vote in the database.
	 * 
	 * First it checks to see if the user has a cookie. case: no cookie -> make
	 * cookie; case cookie -> find cookie. Then it finds the vote and the stand and
	 * updates or create the vote in the database.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Find user
		String email = (String) request.getSession().getAttribute("email");
		
		// get standId from form and find stand in database
		String standIdStr = request.getParameter("standId");
		int standId = Integer.parseInt(standIdStr);

		if (email == null) { // user has no cookie -> make new
			response.sendRedirect("RegistrerBruker?standId=" + standId);
			return;
		}

		// get vote from form
		String votestr = request.getParameter("vote");
		int newVote = Integer.parseInt(votestr);

		// Check for vote to bewithin boundaries
		if (newVote < 0)
			newVote = 0;
		if (newVote > 5)
			newVote = 5;

		// Save new vote in db or update old
		Vote vote = DbHandler.getVoteByUserForStand(email, standId);
		if (vote != null) { // vote for stand exists
			DbHandler.updateVote(email, standId, newVote);
		} else {
			DbHandler.newVote(email, standId, newVote);
		}

		// send to result servlet.
		response.sendRedirect("ResultServlet?standId=" + standId + "&vote=" + newVote);
	}

	/**
	 * doGet(..) receives a parameter from the url connected to the QR-code. The
	 * parameter is the standId used to identify the stand in the database. It then
	 * finds the stand and, if the user has voted before, the vote from the user.
	 * 
	 * The current login-system is hidden from the user through cookies, but this
	 * should be implemented in a different way to make it more secure.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get StandID from parameter
		String standIdStr = request.getParameter("id");
		int standId = Integer.parseInt(standIdStr);

		if (!LoginUtil.brukerIsLoggedIn(request)) {
			response.sendRedirect("RegistrerBruker?standId=" + standId);
			return;
		}
		
		String email = (String) request.getSession().getAttribute("email");
		// find old vote
		Vote vote = DbHandler.getVoteByUserForStand(email, standId);
		request.setAttribute("vote", vote);

		// find Stand in db and setup attribute "stand"
		Stand stand = DbHandler.getStand(standId);
		request.setAttribute("stand", stand);

		request.getRequestDispatcher("WEB-INF/jsp/stand.jsp").forward(request, response);
	}
}
