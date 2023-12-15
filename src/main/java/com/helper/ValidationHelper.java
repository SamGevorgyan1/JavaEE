package com.helper;

import com.exceptions.UserAlreadyExistException;
import com.exceptions.UserApiException;
import com.exceptions.UserValidationException;
import com.model.User;
import com.repository.impl.UserRepositoryImpl;

public class ValidationHelper {

    public static final String USER_NAME_REGEX = "[A-Z][a-z]+";
    public static final String USER_NAME_MSG = "user name can not be null or empty";

    public static final String USER_SURNAME_REGEX = "[A-Z][a-z]+";
    public static final String USER_SURNAME_MSG = "user surname can not be null or empty";

    public static final String USER_EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String USER_EMAIL_MSG = "not correct email format";


    public static void userFieldValidator(User user) {

        if (user.getName() != null &&  !user.getName().matches(USER_NAME_REGEX)) {
            throw new UserValidationException(USER_NAME_MSG);
        }
        if (user.getSurname() != null &&  !user.getSurname().matches(USER_SURNAME_REGEX)) {
            throw new UserValidationException(USER_SURNAME_MSG);
        }
        if (user.getAge() <= 0) {
            throw new UserValidationException("user age can not be negative");
        }
        if (user.getEmail() != null && !user.getEmail().matches(USER_EMAIL_REGEX)) {
            throw new UserValidationException(USER_EMAIL_MSG);
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("not correct password");
        }
    }

    public static void validateDuplicates(String email) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = null;
        try {
            user = userRepository.getByEmail(email);
        } catch (Throwable e) {
            throw new UserApiException();
        }
        if (user != null) {
            throw new UserAlreadyExistException("user found with given email");
        }
    }

    public static boolean isValidPassword(String password) {

        if (password.trim().length() < 8) {
            return false;
        }
        int countOfUppercase = 0;
        int countOfDigits = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                countOfDigits++;
            } else if (Character.isUpperCase(c)) {
                countOfUppercase++;
            }
        }

        if (countOfDigits < 2 && countOfUppercase < 1) {
            return false;
        }
        return true;
    }
}
