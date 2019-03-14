package servlets;

import entities.Stand;
import db.DbHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ResultServlet", urlPatterns = "/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Not needed, but can be used to fx. find old votes for the user and thereby create a loop for the webapp. 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String standIdstr = request.getParameter("standId");
		int standId = Integer.parseInt(standIdstr);
		Stand stand = DbHandler.getStand(standId);
		

		double avg = DbHandler.findAverageVote(standId);

		String vote = request.getParameter("vote");

		request.setAttribute("avg", avg);
		request.setAttribute("stand", stand);
		request.setAttribute("vote", vote);
		request.getRequestDispatcher("WEB-INF/jsp/result.jsp").forward(request, response);
	}
}
