package com.elearning.elearning.security.authentication;

import com.elearning.elearning.account.AccountRepository;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.request.ChangePassword;
import com.elearning.elearning.security.request.Login;
import com.elearning.elearning.security.token.TokenService;
import com.elearning.elearning.verification.VerificationRequest;
import com.elearning.elearning.verification.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.AccountMessage.ACCOUNT_SAVE;
import static com.elearning.elearning.messages.AccountMessage.PASSWORD_UPDATE;
import static com.elearning.elearning.messages.Security.DISCONNECT;
import static com.elearning.elearning.messages.Security.INCORRECT_CREDENTIALS;


@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationResource {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final VerificationService verificationService;
    private final TokenService tokenService;
    private final LocalService localService;
    private final AuthenticationService authenticationService;

    @Override
    public Response authentication(Login login){
        return getResponse(login);
    }

    private Response getResponse(Login login) {
        Optional.ofNullable(accountRepository.findByUsernameIgnoreCase(login.getUsername())).ifPresentOrElse(account -> {}, ()->{
            throw new NotFoundException(NO, localService.getMessage(INCORRECT_CREDENTIALS));
        });


        if(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(),
                        login.getPassword())).isAuthenticated())
           return   tokenService.generateToken(login);
        return null;
    }

    @Override
    public Response authenticationMobile(Login login){
        return getResponse(login);
    }




    @Override
    public UserDetails profile(Principal principal) {
        return authenticationService.loadUserByUsername(authenticationService.currentAccount().getUsername());
    }


    @Override
    public Response verification(VerificationRequest request){
        verificationService.verification(request);
        return new Response(OK,localService.getMessage(ACCOUNT_SAVE));
    }

    @Override
    public Response changePassword(ChangePassword request){
        authenticationService.changePassword(request);
        return new Response(OK,localService.getMessage(PASSWORD_UPDATE));
    }

    @Override
    public Response logout() {
        authenticationService.logout();
        return new Response(OK,localService.getMessage(DISCONNECT));
    }


    @Override
    public Response refreshToken(Login login){
        return tokenService.generateNewToken(login);


    }





}
