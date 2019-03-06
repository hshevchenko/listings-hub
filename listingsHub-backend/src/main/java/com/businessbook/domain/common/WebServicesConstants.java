package com.businessbook.domain.common;

public interface WebServicesConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String USERS_SIGN_UP_URI = "/users/sign-up";
    public static final String USERS_LOGIN_URI = "/login";
    public static final String ALL_URI = "/**";
    
    
    
}
