package com.controller;

import com.enums.Status;
import com.model.User;
import com.repository.impl.UserRepositoryImpl;
import com.util.PasswordEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userRepository.getByEmail(email);


        if (user == null) {
            request.setAttribute("errorMessage", "Wrong email or password");
            request.getRequestDispatcher("/home-page.jsp").forward(request, response);
            return;
        }

        String passwordDb = user.getPassword();
        String encodePassword = PasswordEncoder.encode(password);

        if (!passwordDb.equals(encodePassword)) {

            request.setAttribute("errorMessage", "Invalid credential");
            request.getRequestDispatcher("/home-page.jsp").forward(request, response);
            return;
        }
        if (user.getStatus().equals(Status.INACTIVE)) {
            request.setAttribute("errorMessage", "User is not verified");
            request.getRequestDispatcher("/home-page.jsp").forward(request, response);
            return;
        }
        session.setAttribute("user", user);
        request.getRequestDispatcher("/account-page.jsp").forward(request, response);


    }
}
