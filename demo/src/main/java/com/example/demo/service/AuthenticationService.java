package com.example.demo.service;


import com.example.demo.DTO.JwtAuthenticationResponse;
import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.UserRegistrationDTO;
import com.example.demo.modelo.Rol;
import com.example.demo.modelo.User;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class AuthenticationService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(UserRegistrationDTO request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Rol.Cliente);

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, user.getEmail(), user.getRole());
    }

    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, user.getEmail(), user.getRole());
    }

}

