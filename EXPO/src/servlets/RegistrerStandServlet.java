package servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbHandler;
import entities.Stand;
import login.JavaEmail;
import login.LoginUtil;
import login.PasswordUtil;
import login.PasswordUtil.CannotPerformOperationException;
import login.PasswordUtil.InvalidHashException;

@WebServlet("/RegistrerStandServlet")
public class RegistrerStandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/registrerstand.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Step 1: get pinkode
		if (request.getParameter("needPin") != null) {
			String email = request.getParameter("email");
			String pin = LoginUtil.generatePin();

			try {
				JavaEmail.send(email, pin);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			try {
				String hashPin = PasswordUtil.createHash(pin);
				request.setAttribute("pin", hashPin);
			} catch (CannotPerformOperationException e) {
				e.printStackTrace();
				request.setAttribute("pin", "");
			}
			
			request.setAttribute("email", email);
			request.getRequestDispatcher("WEB-INF/jsp/registrerstandPIN.jsp").forward(request, response);
			return;
		}
		
		//Step 2: check pin
		String email = request.getParameter("email");
		String hashPin = request.getParameter("pin");
		String pinIn = request.getParameter("pinIn");
		boolean correctPin = false;
		try {
			correctPin = PasswordUtil.verifyPassword(pinIn, hashPin);
		} catch (CannotPerformOperationException | InvalidHashException e) {
			e.printStackTrace();
		}
		
		if(!correctPin) {
			request.setAttribute("error", "Feil pin");
			request.setAttribute("email", email);
			request.getRequestDispatcher("WEB-INF/jsp/registrerstandPIN.jsp").forward(request, response);
			return;
		}
		
		Stand stand = new Stand();
		stand.setEpostadmin(email);
		stand.setPin(pinIn);
		DbHandler.newStand(stand);
		LoginUtil.loginStandAdmin(request, stand);
		response.sendRedirect("StandAdmin");
	}

}
