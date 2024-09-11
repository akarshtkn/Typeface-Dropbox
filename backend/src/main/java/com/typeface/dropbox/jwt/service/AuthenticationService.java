package com.typeface.dropbox.jwt.service;

import com.typeface.dropbox.jwt.dto.AuthenticationRequest;
import com.typeface.dropbox.jwt.dto.SignupRequest;
import com.typeface.dropbox.jwt.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    User registerUser(SignupRequest signupRequest);

    String generateToken(UserDetails userDetails);

    User authenticate(AuthenticationRequest authenticationRequest);
}
