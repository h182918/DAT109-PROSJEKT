package Servlets;

import Entities.Stand;
import Entities.Vote;
import db.DbHandler;
import login.Common;
import login.CookieHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "StandServlet", urlPatterns = "/Stand")
public class StandServlet extends HttpServlet {

    private DbHandler db;

    @Override
    public void init() throws ServletException {
        super.init();
        db = new DbHandler();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Find usercookie
        Cookie userCookie = CookieHandler.findCookie(request);

        if(userCookie == null) { //user has no cookie -> make new
            userCookie = CookieHandler.newCookie();
            response.addCookie(userCookie);
        }

        //get vote from form
        String votestr = request.getParameter("vote");
        int newVote = Integer.parseInt(votestr);

        //get standId from form and find stand in database
        String standIdStr = request.getParameter("standId");
        int standId = Integer.parseInt(standIdStr);
        Stand stand = db.getStand(standId);

        //Save new vote in db or update old
        Vote vote = db.getVoteByUserForStand(userCookie.getValue(),standId);
        if(vote != null) { // vote for stand exists
            db.updateVote(userCookie.getValue(),standId,newVote);
            vote = db.getVoteByUserForStand(userCookie.getValue(),standId); //get updated vote
        } else {
            db.newVote(userCookie.getValue(),standId,newVote);
        }

        request.setAttribute("stand",stand);
        request.setAttribute("vote",vote);
        //send to result servlet.
        response.sendRedirect("ResultServlet?standId="+stand.getId()+"&vote="+vote.getScore());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get StandID from parameter
        String standIdStr = request.getParameter("id");
        int standId = Integer.parseInt(standIdStr);

        //find Stand in db and setup attribute "stand"
        Stand stand = db.getStand(standId);
        if (stand == null) {
            throw new ServletException();
        }

        //Find users-cookie if present.
        Cookie userCookie = CookieHandler.findCookie(request);

        if (userCookie != null) {
            //If user has voted on stand before, find vote
            Vote vote = db.getVoteByUserForStand(userCookie.getValue(), standId);
            request.setAttribute("vote", vote);
        }

        request.setAttribute("stand", stand);
        request.getRequestDispatcher("WEB-INF/jsp/stand.jsp").forward(request, response);
    }
}
