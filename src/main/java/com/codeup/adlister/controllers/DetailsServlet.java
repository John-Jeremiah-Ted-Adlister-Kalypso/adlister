package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.codeup.adlister.dao.DaoFactory.*;
import static java.lang.Integer.parseInt;

@WebServlet(name = "controllers.DetailsServlet", urlPatterns = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        Ad displayAd = null;
//        Ad testAd = new Ad(1, 1, "Look at this great ad", "It's right here for you.");
        List<Ad> adList = getAdsDao().all();
        for (Ad ad : adList) {
            if (ad.getId() == id) {
                displayAd = ad;
            }
        }
        if (displayAd == null) {
            response.getWriter().println("<h1>Hey, sorry, something went wrong</h1>");
        } else {
            User displayUser = DaoFactory.getUsersDao().findByUserID(displayAd.getUserId());
            User user = (User)request.getSession().getAttribute("user");
            if (user.getId() == displayUser.getId()) {
                request.setAttribute("isOwner", true);
            }
            request.setAttribute("displayAd", displayAd);
            request.setAttribute("displayUser", displayUser);
            request.getRequestDispatcher("/WEB-INF/ads/details.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ad ad = (Ad)request.getAttribute("displayAd");
        DaoFactory.getAdsDao().deleteByID(ad.getId());
        response.sendRedirect("/profile");
    }
}
