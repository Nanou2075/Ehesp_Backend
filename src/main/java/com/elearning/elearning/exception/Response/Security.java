package com.elearning.elearning.exception.Response;

public class Security {
    public static final Integer NO = 0;
    public static final Integer T = 3;
    public static final Integer OK = 1;
   public static final String[] PUBLIC_URLS = {
            "/auth/**",
            "/account/agent/save",
            "/account/agency/save",
            "/account/company/save",
            "/account/customer/save",
            "/forgetPassword",
            "/refreshToken",
            "/verification/tfa",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };


}
