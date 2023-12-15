package com.controller;



import com.exceptions.ErrorMessages;
import com.helper.ValidationHelper;
import com.model.User;
import com.repository.impl.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ForgotPasswordServlet extends HttpServlet {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        User userByEmail = userRepository.getByEmail(email);

        if (userByEmail != null) {
            userRepository.saveResetToken(email);
            session.setAttribute("email", email);
            response.sendRedirect("/set-password-page.jsp");
        } else {
            request.setAttribute("errorMessage", ErrorMessages.USER_NOT_FOUND_WITH_EMAIL);
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resetToken = request.getParameter("resetToken");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmNewPassword");

        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();

        if (newPassword != null) {
            boolean validPassword = ValidationHelper.isValidPassword(newPassword);
            if (!validPassword) {
                request.setAttribute("errorMessage", "Not correct password.");
                request.getRequestDispatcher("/set-password-page.jsp").forward(request, response);
                return;
            } else if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "Password don't match.");
                request.getRequestDispatcher("/set-password-page.jsp").forward(request, response);
                return;
            }

            User user = userRepository.getByEmail(email);
            if (!user.getResetToken().equals(resetToken)) {
                request.setAttribute("errorMessage", "Reset token don't match.");
                request.getRequestDispatcher("/set-password-page.jsp").forward(request, response);
                return;
            }

            userRepository.resetPassword(email, newPassword);
            session.removeAttribute("email");
            response.sendRedirect("/home-page.jsp");
        }
    }
}


