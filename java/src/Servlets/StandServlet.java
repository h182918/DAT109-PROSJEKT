package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StandServlet", urlPatterns = "/Stand")
public class StandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO
        //get vote from form

        //Save vote in db

        //send to result servlet.
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO
        //get StandID from parameter

        //find Stand in db and setup attribute "stand"

        request.getRequestDispatcher("WEB-INF/jsp/stand.jsp").forward(request,response);
    }
}
