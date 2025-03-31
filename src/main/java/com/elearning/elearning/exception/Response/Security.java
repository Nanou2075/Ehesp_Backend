package com.elearning.elearning.exception.Response;

public class Security {
    public static final Integer NO = 0;
    public static final Integer T = 3;
    public static final Integer OK = 1;
   public static final String[] PUBLIC_URLS = {
            "/auth/**",
            "/forgetPassword",
            "/refreshToken",
           "/level/all",
           "/student/save",
           "/speciality/level",
            "/verification/tfa",
           "cover/**",
           "video/**",
           "book/**",
           "podcast/**"



};
}
