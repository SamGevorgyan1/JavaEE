package com.controller;



import com.helper.ValidationHelper;
import com.model.User;
import com.repository.impl.UserRepositoryImpl;
import com.util.PasswordEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        User user =(User) session.getAttribute("user");
        User userDb = userRepository.getByEmail(user.getEmail());
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!userDb.getPassword().equals(PasswordEncoder.encode(oldPassword))){
            request.setAttribute("error massage", "wrong old password");
            request.getRequestDispatcher("/change-password-page.jsp").forward(request,response);
            return;
        }
        boolean validPassword = ValidationHelper.isValidPassword(newPassword);
        if (!validPassword){
            request.setAttribute("error massage", "password is not valid");
        request.getRequestDispatcher("/change-password-page.jsp").forward(request,response);
        return;
    }
        if (!newPassword.equals(confirmPassword)){
            request.setAttribute("error massage", "passwords don't same ");
            request.getRequestDispatcher("/change-password-page.jsp").forward(request,response);
            return;
        }
        userRepository.resetPassword(user.getEmail(),newPassword);
        User updateUser = userRepository.getByEmail(user.getEmail());
        session.setAttribute("user","updateUser");
        response.sendRedirect("/account-page.jsp");

    }
}
