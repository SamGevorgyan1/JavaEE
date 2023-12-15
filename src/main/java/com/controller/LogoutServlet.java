package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = session.getId();
        String keyOfCookie =null;
        Cookie[] cookies = request.getCookies();


        for (Cookie cookie:cookies){
            if (cookie.getValue().equals(id)){
                keyOfCookie=cookie.getName();

            }
            if (keyOfCookie==null){
                Cookie cookie1 = new Cookie(keyOfCookie,"");
                cookie1.setMaxAge(0);
                response.addCookie(cookie1);
                response.sendRedirect("/login-page.jsp");
            }

        }
    }
}
