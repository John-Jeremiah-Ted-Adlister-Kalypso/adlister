package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "controllers.DetailsServlet", urlPatterns = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        Ad displayAd = null;
//        Ad testAd = new Ad(1, 1, "Look at this great ad", "It's right here for you.");
        List<Ad> adList = DaoFactory.getAdsDao().all();
        for (Ad ad : adList) {
            if (ad.getId() == id) {
                displayAd = ad;
            }
        }
        if (displayAd == null) {
            response.getWriter().println("<h1>Hey, sorry, something went wrong</h1>");
        } else {
            request.setAttribute("displayAd", displayAd);

            request.getRequestDispatcher("/WEB-INF/ads/details.jsp").forward(request, response);
        }
    }
}
