package com.codeWithMerald.RoyalSealLearningSystem.controllers;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Role;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.auth.LoginRequest;
import com.codeWithMerald.RoyalSealLearningSystem.payload.auth.NewPasswordRequest;
import com.codeWithMerald.RoyalSealLearningSystem.payload.auth.PasswordResetRequest;
import com.codeWithMerald.RoyalSealLearningSystem.payload.auth.SignUpRequest;
import com.codeWithMerald.RoyalSealLearningSystem.responses.Response;
import com.codeWithMerald.RoyalSealLearningSystem.services.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;
    private ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("signup")
    public ResponseEntity<Response<String>> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.createStudent(modelMapper.map(signUpRequest, Student.class));
        Response<String> response = new Response<>(HttpStatus.CREATED);
        response.setMessage("You have successfully signed up with Royalseal");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("verifyEmail/{token}")
    public ResponseEntity<Response<String>> verifyUser(@PathVariable String token) {
        authService.verifyUser(token);
        Response<String> response = new Response<>(HttpStatus.ACCEPTED);
        response.setMessage("You are now a verified user of Royalseal");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("login")
    public ResponseEntity<Response<String>> signInUser(@Valid @RequestBody LoginRequest loginRequest,
                                                       HttpServletResponse response) {
        authService.signInUser(loginRequest.getEmail(), loginRequest.getPassword(), response);
        Response<String> res = new Response<>(HttpStatus.OK);
        res.setMessage("User successfully logged in");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("password-reset")
    public ResponseEntity<Response<String>> resetPassword(@Valid @RequestBody
                                                                  PasswordResetRequest passwordResetRequest) {
        authService.resetPassword(passwordResetRequest.getEmail());
        Response<String> response = new Response<>(HttpStatus.OK);
        response.setMessage("A password reset link has been sent to your email");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("set-new-password")
    public ResponseEntity<Response<String>> setNewPassword(@RequestParam("id") Long id,
                                                           @Valid @RequestBody NewPasswordRequest newPasswordRequest,
                                                           @RequestParam String token) {
        authService.setNewPassword(id, newPasswordRequest.getNewPassword(), token);
        Response<String> response = new Response<>(HttpStatus.OK);
        response.setMessage("Password successfully reset");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("user")
    public ResponseEntity<Response<Student>> changeUserRole(@RequestParam Long id, @RequestParam Role role) {
        Student student = authService.changeUserRole(id, role);
        Response<Student> response = new Response<>(HttpStatus.ACCEPTED);
        response.setMessage("User role successfully changed to " + role);
        response.setData(student);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}