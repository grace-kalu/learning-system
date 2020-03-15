package com.codeWithMerald.RoyalSealLearningSystem.models.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }
}
