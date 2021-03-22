package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User)request.getSession().getAttribute("user");
        Long userID = user.getId();
        request.setAttribute("ads",DaoFactory.getAdsDao().byUserID(userID));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String avatar_filepath = request.getParameter("avatar_filepath");
        System.out.println("avatar_filepath = " + avatar_filepath);
        User user = (User)request.getSession().getAttribute("user");
    DaoFactory.getUsersDao().updateAvatar(avatar_filepath, user.getId());
        response.sendRedirect("/profile");

    }
}
