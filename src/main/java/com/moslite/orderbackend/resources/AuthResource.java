package com.moslite.orderbackend.resources;

import com.moslite.orderbackend.security.JWTUtil;
import com.moslite.orderbackend.security.UserSecurity;
import com.moslite.orderbackend.services.UserService;
import com.moslite.orderbackend.services.exceptions.AuthorizationException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSecurity user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

}
