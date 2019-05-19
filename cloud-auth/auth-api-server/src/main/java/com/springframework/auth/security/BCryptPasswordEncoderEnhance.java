package com.springframework.auth.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author summer
 */
public class BCryptPasswordEncoderEnhance extends BCryptPasswordEncoder {
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String presentedPassword = passwordEncoder.encode(rawPassword);
        return passwordEncoder.matches(rawPassword, presentedPassword);
    }

}
