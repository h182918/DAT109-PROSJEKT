package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.DbHandler;
import entities.Stand;
import entities.Vote;
import entities.standOverview;
import login.LoginUtil;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This servlet Tests to see if the admin is logged in, if true the the
	 * parameter 'overview' is set holding all the scores for the stands, and the
	 * admin is forwarded to the overview page.
	 * If the admin isn't logged in he/she must do so first.
	 * 
	 * This method also works to control the page requested by the admin.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (LoginUtil.adminIsLoggedIn(request)) {
			if (request.getParameter("jsp").equals("1")) {
				List<standOverview> overview = DbHandler.getAllVotes();
				request.setAttribute("overview", overview);
				request.getRequestDispatcher("WEB-INF/jsp/adminscore.jsp").forward(request, response);
			} else if (request.getParameter("jsp").equals("2")) {

				if (request.getParameter("update") != null) {
					request.setAttribute("msg", "Standen er oppdatert");
				}

				Stand stand = DbHandler.getStand(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("stand", stand);
				request.getRequestDispatcher("WEB-INF/jsp/adminoppdater.jsp").forward(request, response);
			} else {
				LoginUtil.invalidateLogin(request, response);
			}

		} else if (request.getSession() != null) {
			LoginUtil.invalidateLogin(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}

	}

	/**
	 * Updates the database and sends to doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pin = request.getParameter("pin");
		String imageurl = request.getParameter("imageurl");
		Stand stand = new Stand(id, name, imageurl, email, pin);

		DbHandler.updateStand(stand);

		response.sendRedirect("admin?jsp=2&update=ok&id=" + id);

	}

}
