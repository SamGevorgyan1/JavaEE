package com.controller;



import com.model.Address;
import com.model.User;
import com.repository.impl.AddressRepositoryImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        AddressRepositoryImpl addressRepository = new AddressRepositoryImpl();
        int id = user.getId();
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String home = request.getParameter("home");

        Address address = Address.builder()
                .country(country)
                .city(city)
                .street(street)
                .home(home)
                .userId(id)
                .build();

        addressRepository.saveAddress(address);
        response.sendRedirect("/account-page.jsp");
    }
}
