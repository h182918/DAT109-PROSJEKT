package login;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Stand;

public class LoginUtil {

	/**
	 * Tests if the admin is logged in
	 * @param request
	 * @return
	 */
	public static boolean adminIsLoggedIn(HttpServletRequest request) {
		if(request.getSession().getAttribute("admin") != null) 
			return request.getSession().getAttribute("admin").equals(Common.ADMIN);
		return false;
	}
	
	/**
	 * Tests if stand admin is logged in
	 * @param request
	 * @return
	 */
	public static boolean standAdminIsLoggedIn(HttpServletRequest request) {
		return request.getSession() != null;
	}
	
	/**
	 * Logs in the admin
	 * @param request
	 */
	public static void loginAdmin(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		// invalider gammel sesjon
		if (session != null) {
			session.invalidate();
		}
		
		session = request.getSession(true);
		session.setMaxInactiveInterval(60 * 30); // Expire session after 30min.
		session.setAttribute("admin", Common.ADMIN);
	}

	/**
	 * logs out and sends to login.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void invalidateLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	/**
	 * Logs in stand admin
	 * @param request
	 * @param stand
	 */
	public static void loginStandAdmin(HttpServletRequest request, Stand stand) {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		session = request.getSession(true);
		session.setMaxInactiveInterval(60 * 30); // Expire session after 30min.
		session.setAttribute("stand", stand);
	}
	
	/**
	 * Generates a pinkode, 4 cifers long and random nummbers between 0-9
	 * @return
	 */
	public static String generatePin() {
		String num = "1234567890";
		char[] numchar = num.toCharArray();
		Random rand = new Random();
		String pin = "";
		
		for(int i = 0; i<4;i++) {
			pin += numchar[rand.nextInt(numchar.length)];
		}
		return pin;
	}

	/**
	 * Loggs in the user
	 * @param request
	 * @param email
	 */
	public static void loginBruker(HttpServletRequest request, String email) {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		session = request.getSession(true);
		session.setAttribute("email", email);
		session.setMaxInactiveInterval(60 * 60 * 5); // Expire session after 5 hours.		
	}

	/**
	 * Checks if the user is logged in
	 * @param request
	 * @return
	 */
	public static boolean brukerIsLoggedIn(HttpServletRequest request) {
		if(request.getSession().getAttribute("email") != null) {
			return true;
		}
		return false;
	}
	
}
