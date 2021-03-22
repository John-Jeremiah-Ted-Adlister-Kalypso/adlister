package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ProfileEditServlet", urlPatterns = "/profileedit")
public class ProfileEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/profileedit.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        User user = DaoFactory.getUsersDao().findByUsername(username);
//        User user_cookie = (User)request.getSession().getAttribute("user");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("user.getPassword() = " + user.getPassword());
//        System.out.println("user_cookie.getPassword() = " + user_cookie.getPassword());
        boolean validAttempt = Password.check(password, user.getPassword());
        if (validAttempt) {
            String newpassword = request.getParameter("updatepassword");
            String updatedEmail = request.getParameter("email");
            User updateUser = new User(user.getId(),user.getUsername(), updatedEmail, newpassword);
            DaoFactory.getUsersDao().updateUser(updateUser);
            request.getSession().invalidate();
            request.setAttribute("alert", " <div class='alert alert-success alert-dismissible fade show' role='alert'>Success! Your profile has been updated! Please login to confirm changes!");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else {
            request.setAttribute("user", user);
            request.setAttribute("alert", "Your password did not match the one one file. Please try again.");
            request.getRequestDispatcher("/WEB-INF/profileedit.jsp").forward(request, response);
        }
    }
}