package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StandAdminServlet
 */
@WebServlet("/StandAdminServlet")
public class StandAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!LoginUTil.) {
			response.sendRedirect("LoginServlet"); //evt sende med error som parameter?
			return;
		}else {
				request.getRequestDispatcher("WEB-INF/jsp/AdminStand.jsp").forward(request,response);
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
