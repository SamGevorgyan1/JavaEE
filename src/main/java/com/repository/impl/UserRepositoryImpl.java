package com.repository.impl;

import com.enums.Status;
import com.model.User;
import com.repository.UserRepository;
import com.util.EmailSenderYandex;
import com.util.MyDataSource;
import com.util.PasswordEncoder;
import com.util.TokenGenerate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private static Connection connection = MyDataSource.getConnection();

@Override
    public  User saveUser(User user) {
        String verifyCode = TokenGenerate.generateVerifyCode();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user values (0, ?, ?, ?, ?, ?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, PasswordEncoder.encode(user.getPassword()));
            preparedStatement.setString(6, verifyCode);
            preparedStatement.setString(7, user.getStatus().toString());
            preparedStatement.setString(8, user.getResetToken());
            preparedStatement.executeUpdate();

            EmailSenderYandex.sendEmail(user.getEmail(), "Verification massage", "Code for verification " + verifyCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
@Override
    public  User getByEmail(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String verifyCode = resultSet.getString("verification_code");
                String status = resultSet.getString("status");
                String resetToken = resultSet.getString("reset_token");

                user = new User(id, firstName, lastName, age, email, password, verifyCode, Enum.valueOf(Status.class, status), resetToken);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
@Override
    public  boolean verifyUser(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET verification_code = ?, status = ? WHERE email = ?");
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, Status.ACTIVE.toString());
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public  void saveResetToken(String email) {
        String token = TokenGenerate.generateResetToken();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET reset_token = ? WHERE email = ?");
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

            EmailSenderYandex.sendEmail(email, "Reset password", "Token for reset password " + token);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public  void resetPassword(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ?, reset_token = ?  WHERE email = ?");
            preparedStatement.setString(1, PasswordEncoder.encode(password));
            preparedStatement.setString(2, null);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public  boolean updateUser(User user){

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set first_name=?,last_name=?,age=? where id=?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setInt(3,user.getAge());
            preparedStatement.setInt(4,user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
