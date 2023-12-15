package com.repository;

import com.model.User;

public interface UserRepository {
      User saveUser(User user);
     User getByEmail(String email);
      boolean verifyUser(String email);
      void saveResetToken(String email);
      void resetPassword(String email, String password);
      boolean updateUser(User user);

}
