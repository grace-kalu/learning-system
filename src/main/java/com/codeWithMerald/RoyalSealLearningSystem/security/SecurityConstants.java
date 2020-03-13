package com.codeWithMerald.RoyalSealLearningSystem.security;

public class SecurityConstants {
    public static final String AUTH_LOGIN_URL = "/auth/login";

    public static final String AUTH_SIGN_UP_URL = "/auth/signUp";

    public static final String AUTH_VERIFY_URL = "/auth/verifyEmail/**";

    public static final String RESET_PASSWORD_URL = "/auth/password-reset";

    public static final String SET_NEW_PASSWORD_URL = "/auth/set-new-password";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    public static final String JWT_SECRET = "secret-api-key";//"n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
