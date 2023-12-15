package com.controller;

import com.enums.Status;
import com.exceptions.UserAlreadyExistException;
import com.exceptions.UserValidationException;
import com.helper.ValidationHelper;
import com.model.User;
import com.repository.impl.UserRepositoryImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserRepositoryImpl userRepository=new UserRepositoryImpl();

        User user = new User(0, name, surname, Integer.parseInt(age), email, password,null, Status.INACTIVE,null);

        session.setAttribute("user",user);

        try {
            ValidationHelper.userFieldValidator(user);
            ValidationHelper.validateDuplicates(email);
        } catch (Exception e) {
            if (e instanceof UserValidationException || e instanceof UserAlreadyExistException) {
                String message = e.getMessage();
                request.setAttribute("errorMessage", message);
                request.getRequestDispatcher("/registration-page.jsp").forward(request, response);
            } else {
                response.getWriter().println("SORRY");
            }

        }

        userRepository.saveUser(user);
        response.sendRedirect("/verify-page.jsp");
    }
}
