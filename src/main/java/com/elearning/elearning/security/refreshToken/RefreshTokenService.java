package com.elearning.elearning.security.refreshToken;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.Security.REFRESH_EXPIRED;
import static com.elearning.elearning.messages.Security.REFRESH_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final LocalService localService;

    /**
     *
     * @param value token value to check
     */
    public void cheekRefreshToken(String value) {
        refreshTokenRepository.findByValue(value).ifPresentOrElse(refreshToken -> {
           if (refreshToken.getExpireTime().isBefore(Instant.now()))
                    throw new NotFoundException(NO,localService.getMessage(REFRESH_EXPIRED));},
                ()-> {throw new NotFoundException(NO,localService.getMessage(REFRESH_NOT_FOUND));
        });
    }


    /**
     *
     * @param value  the token value account
     * @param expire the token expire time
     * @param account the token value account
     * @return the new token
     */
    public RefreshToken saveRefreshToken(String value,
                                         Instant expire,
                                         Account account){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setValue(value);
        refreshToken.setExpireTime(expire);
        refreshToken.setAccount(account);
      return   refreshTokenRepository.save(refreshToken);

    }

    public void deleteRefreshToken(Account account){
        refreshTokenRepository.deleteAllByAccount(account);
    }









}

