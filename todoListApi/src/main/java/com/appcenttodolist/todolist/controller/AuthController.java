package com.appcenttodolist.todolist.controller;

import com.appcenttodolist.todolist.entity.User;
import com.appcenttodolist.todolist.request.UserLoginRequest;
import com.appcenttodolist.todolist.request.UserSignupRequest;
import com.appcenttodolist.todolist.response.AuthResponse;
import com.appcenttodolist.todolist.security.JwtTokenProvider;
import com.appcenttodolist.todolist.service.abstracts.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService, PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                        loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody UserSignupRequest userSignupRequest) {
        AuthResponse authResponse = new AuthResponse("Init");
        if (userService.existsByUserName(userSignupRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(userSignupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Error: Email is already in use!"));
        }

        User user = new User();
        user.setUserName(userSignupRequest.getUserName());
        user.setEmail(userSignupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));
        userService.saveOneUser(user);
        authResponse.setMessage("User successfully SignUp.");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

}


