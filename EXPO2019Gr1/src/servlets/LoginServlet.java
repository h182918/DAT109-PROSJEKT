package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbHandler;
import entities.Stand;
import login.LoginUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String adminUser;
	private String adminPass;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		adminUser = config.getInitParameter("adminUser");
		adminPass = config.getInitParameter("adminPassword");
	}

	/**
	 * if no errors are recorded then page is forwarded.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// check for errors
		if (request.getParameter("error") != null) {
			request.setAttribute("error", "Noe gikk galt, prøv igjen");
		}

		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * Checks email and pinkode and logs in.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String epostIn = request.getParameter("email");
		String passwordIn = request.getParameter("password");
		boolean isAdmin = epostIn.equals(adminUser) && passwordIn.equals(adminPass);

		// user is admin
		if (isAdmin) {
			LoginUtil.loginAdmin(request);
			response.sendRedirect("admin?jsp=1");
			return;
		}

		Stand stand = DbHandler.findStand(epostIn);
		if (stand == null) { // Stand not found
			response.sendRedirect("login" + "?error=1");
			return;
		}

		String pin = stand.getPin();
		if (pin.equals(passwordIn)) {
			LoginUtil.loginStandAdmin(request, stand);
			response.sendRedirect("StandAdmin");
		} else {
			response.sendRedirect("login?error=1");
		}

	}

}
