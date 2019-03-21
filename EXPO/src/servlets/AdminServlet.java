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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(LoginUtil.adminIsLoggedIn(request)) {
			if(request.getParameter("jsp").equals("1")) {
				List<standOverview> overview= DbHandler.getAllVotes();
				request.setAttribute("overview", overview);
				request.getRequestDispatcher("WEB-INF/jsp/adminscore.jsp").forward(request, response);
			}else if(request.getParameter("jsp").equals("2")) {
			//	request.setAttribute("standid", request.getParameter("id"));
				Stand stand = DbHandler.getStand(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("stand", stand);
				request.getRequestDispatcher("WEB-INF/jsp/adminoppdater.jsp").forward(request, response);
			}else {
				LoginUtil.invalidateLogin(request, response);
			}
			
		}else if(request.getSession() != null) {
			LoginUtil.invalidateLogin(request, response);
		}else {
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO
	}

}
