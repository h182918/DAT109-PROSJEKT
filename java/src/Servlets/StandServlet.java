package Servlets;

import Entities.Stand;
import db.DbHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StandServlet", urlPatterns = "/Stand")
public class StandServlet extends HttpServlet {

    private DbHandler db;

    @Override
    public void init() throws ServletException {
        super.init();
        db = new DbHandler();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO
        //Find user in db OR create user in db


        //get vote from form
        String votestr = request.getParameter("vote");

        //get standId
        String standId = request.getParameter("standId");

        //Save vote in db

        //send to result servlet.
        response.sendRedirect("ResultServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO
        Cookie user;
        //Find user in db or make new cookie;
        if(request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            user = cookies[0];
        }

        //get StandID from parameter
        String standId = request.getParameter("id");

        //find Stand in db and setup attribute "stand"
        Stand stand = db.getStand(Integer.parseInt(standId));

        if(stand != null) {
            request.setAttribute("stand", stand);
            request.getRequestDispatcher("WEB-INF/jsp/stand.jsp").forward(request, response);
        }else {
            throw new ServletException();
        }
    }
}
