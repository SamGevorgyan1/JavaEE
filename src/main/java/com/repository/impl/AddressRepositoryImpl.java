package com.repository.impl;

import com.model.Address;
import com.repository.AddressRepository;
import com.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRepositoryImpl implements AddressRepository {
    private static Connection connection = MyDataSource.getConnection();

    @Override
    public Address saveAddress(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO address values(?,?,?,?,?,?) ");
            preparedStatement.setInt(1, address.getAddressId());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getHome());
            preparedStatement.setInt(6, address.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    @Override
    public Address getAddressByUserId(int userId) {
        Address address = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM address where user_id = ?");
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int addressId = resultSet.getInt("address_id");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String home = resultSet.getString("home");
                address = new Address(addressId, country, city, street, home, userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    @Override
    public void updateAddress(Address address) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update address set country=?,city=?, street=?, home=? where address_id=?");
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getHome());
            preparedStatement.setInt(5, address.getAddressId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
