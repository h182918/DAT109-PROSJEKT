package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Stand;

public class LoginUtil {

	public static boolean adminIsLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("admin").equals(Common.ADMIN);
	}
	
	public static boolean standAdminIsLoggedIn(HttpServletRequest request) {
		return request.getSession() != null;
	}
	
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

	public static void invalidateLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	public static void loginStandAdmin(HttpServletRequest request, Stand stand) {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		session = request.getSession(true);
		session.setMaxInactiveInterval(60 * 30); // Expire session after 30min.
		session.setAttribute("stand", stand);
	}

}
