package com.controller;



import com.model.Address;
import com.model.User;
import com.repository.impl.AddressRepositoryImpl;
import com.repository.impl.UserRepositoryImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        AddressRepositoryImpl addressRepository = new AddressRepositoryImpl();
        Address address = addressRepository.getAddressByUserId(user.getId());

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");

        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String home = request.getParameter("home");
        UserRepositoryImpl userRepository= new UserRepositoryImpl();

        user.setName(name);
        user.setSurname(surname);
        user.setAge(Integer.parseInt(age));
        userRepository.updateUser(user);
      Address updateAddress=new Address(0,country,city,street,home, user.getId());
      if (address==null) {
          addressRepository.saveAddress(updateAddress);
      }else {
          addressRepository.updateAddress(updateAddress);
          updateAddress.setAddressId(address.getAddressId());
      }
      response.sendRedirect("/account-page.jsp");
    }
}
