package com.codeWithMerald.RoyalSealLearningSystem.models.user;

public enum Role {
    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }

}
