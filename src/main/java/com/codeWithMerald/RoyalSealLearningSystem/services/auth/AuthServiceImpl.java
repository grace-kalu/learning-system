package com.codeWithMerald.RoyalSealLearningSystem.services.auth;

import com.codeWithMerald.RoyalSealLearningSystem.exception.AppException;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.EmailVerificationStatus;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Role;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.StudentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.security.JwtTokenProvider;
import com.codeWithMerald.RoyalSealLearningSystem.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private StudentRepository studentRepository;
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailSender emailSender;
    private AuthenticationManager authenticationManager;

    @Value("${server.port}")
    private String port;

    @Autowired
    public AuthServiceImpl(StudentRepository studentRepository,
                           JwtTokenProvider jwtTokenProvider,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           EmailSender emailSender,
                           AuthenticationManager authenticationManager) {
        this.studentRepository = studentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailSender = emailSender;
        this.authenticationManager = authenticationManager;
    }

    public void createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())){
            throw new AppException("Email already exists", HttpStatus.CONFLICT);
        }
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setRoles(Collections.singletonList(Role.ROLE_STUDENT));
        String token = jwtTokenProvider.generateToken(student.getEmail());
        student.setEmailVerificationToken(token);
        studentRepository.save(student);

        String url = "http://localhost:"+ port + "/auth/verifyEmail/" + token;
        String message =
                "Hey" + student.getFullName() + ",\n" +
                        "You just created an account with The Royalseal Academy \n" +
                        "You are required to use the following link to verify your account\n" + url + "\n"  +
                        " –Please disregard if it wasn't you";
        emailSender.sendEmail(student.getEmail(), "RoyalSeal Registration Verification", message);
    }

    public void verifyUser(String token) {
        Student student = studentRepository.findByEmailVerificationToken(token);
        if (student == null) {
            throw new AppException("Unable to verify the registration link, please register again", HttpStatus.BAD_REQUEST);
        }
        student.setEmailVerificationStatus(EmailVerificationStatus.VERIFIED);
        studentRepository.save(student);
    }

    public void signInUser(String email, String password, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        if (authentication == null) {
            throw new AppException("User not found", HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Student student = studentRepository.findByEmail(email);
        if (student.getEmailVerificationStatus().equals(EmailVerificationStatus.UNVERIFIED) ) {
            throw new AppException("You haven't verified your account yet", HttpStatus.BAD_REQUEST);
        }
        String token = jwtTokenProvider.createToken(student.getId(), email, student.getRoles(), 86400000);
        response.addHeader("token", token);
    }

    public void resetPassword(String email) {
        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            throw new AppException("Please Register", HttpStatus.BAD_REQUEST);
        }
        String token = jwtTokenProvider.createToken(student.getId(), student.getEmail(),
                student.getRoles(), 3600000);
        System.out.println(token);

        String url = "http://localhost:"+ port + "/auth/password-reset/" + student.getId() + token;

        String message =
                "Hey" + student.getFullName() + ",\n" +
                        "You can use the following link to reset your password:\n" + url + "\n" +
                        "If you don’t use this link within 1 hour, it will expire.\n" +
                        " –Please ignore if it wasn't initiated by you";
        emailSender.sendEmail(student.getEmail(), "RoyalSeal Reset Password", message);
    }

    public void setNewPassword(Long id, String newPassword, String token) {
        if (jwtTokenProvider.isTokenExpired(token)) {
            throw new AppException("The token has expired", HttpStatus.FORBIDDEN);
        }
        String email = jwtTokenProvider.getEmail(token);
        if (!studentRepository.existsByEmail(email)) {
            throw new AppException("There has been a compromise", HttpStatus.BAD_REQUEST);
        }
        Optional<Student> user = studentRepository.findById(id);
        if (user.isPresent()) {
            user.get().setPassword(bCryptPasswordEncoder.encode(newPassword));
            studentRepository.save(user.get());
        } else {
            throw new AppException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student changeUserRole(Long id, Role role) {
        Optional<Student> user = studentRepository.findById(id);
        if (user.isEmpty()) {
            throw new AppException("User does not exist", HttpStatus.NOT_FOUND);
        }
        List<Role> roles = new ArrayList<>(Collections.singletonList(role));
        user.get().setRoles(roles);
        return studentRepository.save(user.get());
    }
}