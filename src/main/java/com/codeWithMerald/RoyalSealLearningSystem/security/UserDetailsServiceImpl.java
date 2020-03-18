package com.codeWithMerald.RoyalSealLearningSystem.security;

import com.codeWithMerald.RoyalSealLearningSystem.exception.AppException;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private StudentRepository studentRepository;

    @Autowired
    public UserDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Student student = studentRepository.findByEmail(email);

        if (student == null) {
            throw new AppException("Email not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(student.getPassword()).authorities(student.getRoles())
                .accountExpired(false).accountLocked(false)
                .credentialsExpired(false).disabled(false).build();
    }
}
