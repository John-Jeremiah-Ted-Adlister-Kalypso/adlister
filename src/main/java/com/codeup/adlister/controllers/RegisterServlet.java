package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("email", request.getParameter("email"));
        if(request.getParameter("alert") != null) {request.setAttribute("alert", request.getParameter("alert"));}
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");
        User userExisting = new User("administrator","admin@adlister.com","password");
        if (DaoFactory.getUsersDao().findByUsername(username) != null) {userExisting = DaoFactory.getUsersDao().findByUsername(username);}
        System.out.println(userExisting.getUsername());
        System.out.println(userExisting.getId());
        System.out.println("userExisting.getUsername().equalsIgnoreCase(username) = " + userExisting.getUsername().equalsIgnoreCase(username));
        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || (! password.equals(passwordConfirmation))
            || userExisting.getUsername().equalsIgnoreCase(username);

        if (inputHasErrors) {
//            if (userExisting.getUsername().equalsIgnoreCase(username)) {request.setAttribute("alert", username);}
            request.setAttribute("username", username);
            request.setAttribute("email", email);;
            response.sendRedirect("/register?username="+username+"&email="+email+"&alert="+username);
            return;
        }

        // create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}
