package com.controller;


import com.exceptions.ErrorMessages;
import com.model.User;
import com.repository.impl.UserRepositoryImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verifyCode = request.getParameter("verifyCode");
        UserRepositoryImpl userRepository =new UserRepositoryImpl();

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String email = sessionUser.getEmail();
        User user = userRepository.getByEmail(email);

        String MSG = null;
        if (user == null) {
            MSG = ErrorMessages.USER_NOT_FOUND_WITH_EMAIL;
        } else if (!user.getVerifyCode().equals(verifyCode)) {
            MSG = "Not correct verify code";
        }
        if (MSG != null) {
            request.setAttribute("errorMessage", MSG);
            request.getRequestDispatcher("/verify-page.jsp").forward(request, response);
            return;
        }

        boolean isVerified = userRepository.verifyUser(email);
        if (isVerified) {
            request.removeAttribute("user");
        }
        response.sendRedirect("/home-page.jsp");
    }
}
