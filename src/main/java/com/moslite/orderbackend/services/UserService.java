package com.moslite.orderbackend.services;

import com.moslite.orderbackend.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSecurity authenticated() {
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
