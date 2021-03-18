package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "controllers.EditServlet", urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        long id = parseInt(request.getParameter("id"));
        Ad displayAd = DaoFactory.getAdsDao().byAdID(id);
        User displayUser = DaoFactory.getUsersDao().findByUserID(displayAd.getUserId());
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId() == displayUser.getId()) {
            request.setAttribute("isOwner", true);
        }
        request.setAttribute("ad", displayAd);
        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        long id = parseInt(request.getParameter("id"));
        System.out.println(id);
//        DaoFactory.getAdsDao().insert(ad);
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("description"));
        DaoFactory.getAdsDao().updateAdByID(id,request.getParameter("title"),request.getParameter("description"));
        response.sendRedirect("/details?id=" + id);
    }
}


