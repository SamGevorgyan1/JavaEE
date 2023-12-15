package com.util;

import java.util.Base64;

public class PasswordEncoder {

    public static String encode(String password) {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.encodeToString(password.getBytes());
    }
}
