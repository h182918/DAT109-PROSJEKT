package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StandAdminServlet")
public class StandAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!LoginUtil.standAdminIsLoggedIn(request)){
			response.sendRedirect("LoginServlet"); //evt sende med error som parameter?
			return;
		}else {
				request.getRequestDispatcher("WEB-INF/jsp/AdminStand.jsp").forward(request,response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String epost = request.getSession().getAttribute("epost");
	Stand stand = DbHandler.findStand(epost);
	String name = request.getParameter("name");
	String image = request.getParameter("image");
	String button = request.getParameter("button");
	
	//mangler metodene i DBhandler for � oppdatere database
	stand.setName(name);
	stand.setImageurl(image);
	
	if("Oppdater".equals(button)) {
		//ordne QR kode
		response.sendRedirect("/standAdminServlet");
	}else if("Logg ut".equals(button)){
		request.getSession(false);
		response.sendRedirect("LoginServlet");
	}
	
	}
}
