package com.codeWithMerald.RoyalSealLearningSystem.services.auth;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Role;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.User;

import javax.servlet.http.HttpServletResponse;

public interface AuthService {
        void createUser(User user);

        void verifyUser(String token);

        void signInUser(String email, String password, HttpServletResponse response);

        void resetPassword(String email);

        void setNewPassword(Long id, String newPassword, String token);

        User changeUserRole(Long id, Role role);
}
