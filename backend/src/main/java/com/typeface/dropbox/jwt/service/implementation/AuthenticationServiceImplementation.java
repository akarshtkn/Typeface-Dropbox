package com.typeface.dropbox.jwt.service.implementation;

import com.typeface.dropbox.commons.exceptions.InvalidCredentialsException;
import com.typeface.dropbox.commons.exceptions.UserAlreadyExistException;
import com.typeface.dropbox.commons.exceptions.UserNotFoundException;
import com.typeface.dropbox.jwt.dto.AuthenticationRequest;
import com.typeface.dropbox.jwt.dto.SignupRequest;
import com.typeface.dropbox.jwt.entity.User;
import com.typeface.dropbox.jwt.repository.UserRepository;
import com.typeface.dropbox.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtServiceImplementation jwtServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImplementation.class);

    @Override
    public User registerUser(SignupRequest signupRequest) {
        logger.info("Attempting to register user with email: {}", signupRequest.getEmail());

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            logger.warn("User registration failed: User already exists with email {}", signupRequest.getEmail());
            throw new UserAlreadyExistException("User already exists with email " + signupRequest.getEmail());
        }

        User newUser= User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build();

        User savedUser = userRepository.save(newUser);
        logger.info("User registered successfully: {}", savedUser.getEmail());
        return savedUser;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        logger.debug("Generating token for user: {}", userDetails.getUsername());
        return jwtServiceImpl.generateToken(userDetails);
    }

    @Override
    public User authenticate(AuthenticationRequest authenticationRequest) {
        logger.info("Authenticating user with email: {}", authenticationRequest.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            logger.info("User authenticated successfully: {}", authenticationRequest.getEmail());
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}. Error: {}", authenticationRequest.getEmail(), e.getMessage());
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        return userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> {
                    logger.warn("User not found during authentication: {}", authenticationRequest.getEmail());
                    return new UserNotFoundException("User not found");
                });
    }
}
