package com.elearning.elearning.security.authentication;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.request.ChangePassword;
import com.elearning.elearning.security.request.Login;
import com.elearning.elearning.verification.VerificationRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping()
public interface AuthenticationResource {

    @PostMapping("auth/logIn")
    Response authentication (@RequestBody Login login);

    @PostMapping("auth/mobile-login")
    Response authenticationMobile (@RequestBody Login login);

    @PostMapping("auth/verification")
    Response verification( @RequestBody VerificationRequest request);

    @PostMapping("change-password")
    Response changePassword(@RequestBody ChangePassword request);

    @GetMapping("logOut")
     Response logout() ;

    @GetMapping(value = "profile")
    UserDetails profile(Principal principal);

    @PostMapping("auth/refreshToken")
    Response refreshToken(@RequestBody Login login);


}
