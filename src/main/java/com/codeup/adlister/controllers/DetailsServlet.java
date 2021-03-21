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
        System.out.println(id);
        Ad displayAd = DaoFactory.getAdsDao().byAdID(id);
        List<String> categories = DaoFactory.getAdsDao().getCategoriesByAdID(id);
        if (displayAd == null) {
            response.getWriter().println("<h1>Hey, sorry, something went wrong</h1>");
        }
        User displayUser = DaoFactory.getUsersDao().findByUserID(displayAd.getUserId());
        if (request.getSession().getAttribute("user") != null)
        {
            User user = (User)request.getSession().getAttribute("user");
            if (user.getId() == displayUser.getId()) {
                request.setAttribute("isOwner", true);
            }}
//        System.out.println("displayAd.getCategories() = " + displayAd.getCategories());
//        System.out.println("displayAd.getCategories().get(0) = " + displayAd.getCategories().get(0));
        request.setAttribute("displayAd", displayAd);
            request.setAttribute("displayUser", displayUser);
            request.getRequestDispatcher("/WEB-INF/ads/details.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = parseInt(request.getParameter("adID"));
        System.out.println(id);
        System.out.println(id);
        DaoFactory.getAdsDao().deleteByID(id);
        response.sendRedirect("/confirmdelete");}

    }

