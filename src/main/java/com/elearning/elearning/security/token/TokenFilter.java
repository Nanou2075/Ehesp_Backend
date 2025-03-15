package com.elearning.elearning.security.token;

import com.elearning.elearning.security.accessToken.AccessTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Configuration
public class TokenFilter extends OncePerRequestFilter {
    private AccessTokenService accessTokenService;
    private  HandlerExceptionResolver handlerExceptionResolver;

    public TokenFilter(AccessTokenService accessTokenService, HandlerExceptionResolver handlerExceptionResolver) {
        this.accessTokenService = accessTokenService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /**
     *
     * @param request the input value
     * @param response the output value
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        final String authorization = request.getHeader("Authorization");
        if (request.getServletPath().contains("/login")){
            filterChain.doFilter(request, response);
        }
        try {
            if (authorization != null && authorization.startsWith("Bearer ")) {
                token = authorization.substring(7);
                accessTokenService.cheekAccessToken(token);
            }
            filterChain.doFilter(request, response);

        }
        catch (Exception ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);

        }

    }

}
