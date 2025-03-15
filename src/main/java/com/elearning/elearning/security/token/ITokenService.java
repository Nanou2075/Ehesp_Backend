package com.elearning.elearning.security.token;




import com.elearning.elearning.account.Account;
import com.elearning.elearning.account.AccountRepository;
import com.elearning.elearning.document.Document;
import com.elearning.elearning.document.DocumentRepository;
import com.elearning.elearning.document.DocumentService;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.security.accessToken.AccessTokenService;
import com.elearning.elearning.security.refreshToken.RefreshToken;
import com.elearning.elearning.security.refreshToken.RefreshTokenService;
import com.elearning.elearning.security.request.Login;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.Security.CONNEXION_SUCCESS;


@Service
@Transactional
@RequiredArgsConstructor

public class ITokenService implements TokenService {
    private final AccountRepository accountRepository;
    private final TokenUtils tokenUtils;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final LocalService localService;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final DocumentRepository documentRepository;


    public CompletableFuture<Optional<Document>> getDocument (String accountId) {
        return CompletableFuture
                .supplyAsync(() -> documentRepository.findByAccountId(accountId));
    }


    /**
     *
     * @param login permit to generate the Token Access for different request
     * @return Jwt-value
     */

    @Override
 public Response generateToken(Login login){

         Account account = accountRepository.findByUsernameIgnoreCase(login.getUsername());
        JwtClaimsSet jwtAccessClaimsSet = JwtClaimsSet.builder()
             .subject(account.getId())
             .issuedAt(tokenUtils.currentTime)
             .expiresAt(tokenUtils.currentTime.plusMillis(tokenUtils.access))
             .claim("username", account.getUsername())

             .build();
     String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtAccessClaimsSet)).getTokenValue();


     JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
             .subject(account.getId())
             .issuedAt(tokenUtils.currentTime)
             .expiresAt(tokenUtils.currentTime.plusMillis(tokenUtils.refresh))
             .claim("username", account.getUsername())
             .build();
     String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtRefreshClaimsSet)).getTokenValue();


     TokenValue tokenValue =  TokenValue.builder()
             .accessToken(jwtAccessToken)
             .refreshToken(jwtRefreshToken)
             .permission(account.getPermission())
             .url(getDocument(account.getId())
                     .join().map(Document::getUrl)
                     .orElse(null))
             .build();
        accessTokenService.deleteAccessToken(account);
        refreshTokenService.deleteRefreshToken(account);
        RefreshToken refreshToken = refreshTokenService.
                saveRefreshToken(tokenValue.getRefreshToken(),
                        tokenUtils.currentTime.plusMillis(tokenUtils.access), account);
     accessTokenService.saveAccessToken(tokenValue.getAccessToken(),
             tokenUtils.currentTime.plusMillis(tokenUtils.access), account, refreshToken);


        return  new Response(OK,localService.getMessage(CONNEXION_SUCCESS), tokenValue);

 }


    /**
     *
     * @param login permit to generate the Token RefreshToken for different request
     * @return Jwt-value
     */
    @Override
    public Response generateNewToken(Login login){
        Jwt decodedRefreshToken = jwtDecoder.decode(login.getRefreshToken());
        String username=decodedRefreshToken.getSubject();
        Account account = accountRepository.findByUsernameIgnoreCase(username);
        login.setUsername(account.getUsername());
        refreshTokenService.cheekRefreshToken(login.getRefreshToken());
        return  generateToken(login);
    }
}
