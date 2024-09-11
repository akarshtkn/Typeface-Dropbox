package com.typeface.dropbox.jwt.controller;

import com.typeface.dropbox.jwt.dto.AuthenticationRequest;
import com.typeface.dropbox.jwt.dto.SignupRequest;
import com.typeface.dropbox.jwt.dto.AuthenticationResponse;
import com.typeface.dropbox.jwt.entity.User;
import com.typeface.dropbox.jwt.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Value("${spring.security.jwt.cookieExpiry}")
    private long cookieExpiry;

    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse>  registerUser(@Valid @RequestBody SignupRequest signupRequest,
                                                                HttpServletResponse response) {
        logger.info("Received signup request for email: {}", signupRequest.getEmail());

        User newUser = authenticationService.registerUser(signupRequest);
        logger.info("User registered successfully: {}", newUser.getEmail());

        String token = authenticationService.generateToken(newUser);
        logger.debug("Generated token for user: {}", newUser.getEmail());

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false) // Set to true in production
                .path("/")
                .maxAge(cookieExpiry)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        AuthenticationResponse authenticationResponse = modelMapper.map(newUser, AuthenticationResponse.class);
        logger.info("Returning authentication response for user: {}", newUser.getEmail());

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest,
                                              HttpServletResponse response) {
        logger.info("Received authentication request for email: {}", authenticationRequest.getEmail());

        User user = authenticationService.authenticate(authenticationRequest);
        logger.info("User authenticated successfully: {}", user.getEmail());

        String token = authenticationService.generateToken(user);
        logger.debug("Generated token for user: {}", user.getEmail());

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false) // Set to true in production
                .path("/")
                .maxAge(cookieExpiry)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        AuthenticationResponse authenticationResponse = modelMapper.map(user, AuthenticationResponse.class);
        logger.info("Returning authentication response for user: {}", user.getEmail());

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        logger.info("User logged out successfully. Invalidating token.");

        // Create a cookie to invalidate the token
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false) // Set to true in production
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        logger.debug("Cookie invalidated and removed from response: {}", cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public String testingAuthentication() {
        return "Working fine...";
    }
}
