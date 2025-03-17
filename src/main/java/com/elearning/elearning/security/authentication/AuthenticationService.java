package com.elearning.elearning.security.authentication;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.account.AccountRepository;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.request.ChangePassword;
import com.elearning.elearning.security.accessToken.AccessTokenService;
import com.elearning.elearning.security.refreshToken.RefreshTokenService;
import com.elearning.elearning.student.Student;
import com.elearning.elearning.student.StudentRepository;
import com.elearning.elearning.training.Training;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.AccountMessage.USER_NOT_FOUND;
import static com.elearning.elearning.messages.Security.INCORRECT_PASSWORD;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final LocalService localService;
    private final StudentRepository studentRepository;

    /**
     *
     * @param username permit to found the account in database
     * @return account info like response
     * @throws UsernameNotFoundException My exception Class
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsernameIgnoreCase(username);
    }

    /**
     *
     * @param username  permit to found the account in database for cheeking
     * @throws NotFoundException My exception Class
     */


    /**
     *
     * @return RegisterConfirmation information
     * @throws NotFoundException My exception Class
     */

    public Account currentAccount() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication.getName();
        return Optional.ofNullable(accountRepository.findAccountById(principal))
                .orElseThrow(() -> new NotFoundException(NO,localService.getMessage(USER_NOT_FOUND)));
    }


    public Training currentTraining() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication.getName();
        return Optional.ofNullable(studentRepository.findStudentById(principal).getTraining())
                .orElseThrow(() -> new NotFoundException(NO,localService.getMessage(USER_NOT_FOUND)));
    }
    public Student currentStudent() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication.getName();
        return Optional.ofNullable(studentRepository.findStudentById(principal))
                .orElseThrow(() -> new NotFoundException(NO,localService.getMessage(USER_NOT_FOUND)));
    }

    /**
     * this method permit to disconnect Account
     */

    public void logout (){
        accessTokenService.deleteAccessToken(currentAccount());
        refreshTokenService.deleteRefreshToken(currentAccount());

    }

    /**
     *
     * @param request permit to change account password
     * @throws NotFoundException My exception Class
     */
    public void changePassword(ChangePassword request) throws NotFoundException {
                if (!passwordEncoder.matches(request.getCurrentPassword(), currentAccount().getPassword()))
                    throw new NotFoundException(NO, localService.getMessage(INCORRECT_PASSWORD));
                 currentAccount().setPassword(passwordEncoder.encode(request.getNewPassword()));
                        accountRepository.save(currentAccount());
    }

}
