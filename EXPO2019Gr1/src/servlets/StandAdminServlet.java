package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbHandler;
import entities.Stand;
import login.LoginUtil;

@WebServlet("/StandAdminServlet")
public class StandAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Checks if you're correctly logged in, then sets parameters 'stand' and 'stand id' and forwards.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!LoginUtil.standAdminIsLoggedIn(request)) {
			response.sendRedirect("login?error=1"); 
			return;
		} else {
			Stand stand = (Stand) request.getSession().getAttribute("stand");
			int id = stand.getId();
			String link = "http://data1.hib.no:9090/EXPO2019Gr1/Stand?id=" + id;
			request.setAttribute("qrlink", link);
			request.getRequestDispatcher("WEB-INF/jsp/AdminStand.jsp").forward(request, response);
		}
	}

	/**
	 * Updates database and sends redirect.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Stand stand = (Stand) request.getSession().getAttribute("stand");
	
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String image = request.getParameter("imageurl");
		String pin = request.getParameter("pin");

		stand.setName(name);
		stand.setEpostadmin(email);
		stand.setImageurl(image);
		stand.setPin(pin);
		
		DbHandler.updateStand(stand);
		
		String link = "http://data1.hib.no:9090/EXPO2019Gr1/Stand?id=" + stand.getId();
		request.setAttribute("qrlink", link);
		request.setAttribute("msg", "Standen er oppdatert");
		response.sendRedirect("StandAdmin");
	}
}
