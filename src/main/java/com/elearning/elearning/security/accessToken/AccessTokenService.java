package com.elearning.elearning.security.accessToken;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.common.InjectionService;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.security.refreshToken.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.Security.ACCESS_EXPIRED;
import static com.elearning.elearning.messages.Security.ACCESS_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional
public class AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;
    private final LocalService localService;



    /**
     *
     * @param value the token value to check
     */
    public void cheekAccessToken(String value) {
        accessTokenRepository.findByValue(value).ifPresentOrElse(accessToken -> {
               if (accessToken.getExpireTime().isBefore(Instant.now()))
                    throw new NotFoundException(NO,localService.getMessage(ACCESS_EXPIRED));},
                ()-> {throw new NotFoundException(NO,localService.getMessage(ACCESS_NOT_FOUND));
        });
    }


    /**
     *
     * @param value  the token value to check
     * @param expire the token value expire time
     * @param account the token value user
     * @param refreshToken the token value to check for get new Access Token
     */
    public void saveAccessToken(String value,
                                Instant expire,
                                Account account,
                                RefreshToken refreshToken){
        AccessToken accessToken = new AccessToken();
        accessToken.setValue(value);
        accessToken.setExpireTime(expire);
        accessToken.setAccount(account);
        accessToken.setRefreshToken(refreshToken);
        accessTokenRepository.save(accessToken);
    }

    /**
     *
     * @param account the token value user
     */
    public void deleteAccessToken(Account account){
        accessTokenRepository.deleteAllByAccount(account);
    }









}

