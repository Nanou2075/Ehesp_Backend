package com.elearning.elearning.security.token;



import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.security.request.Login;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface TokenService {
    Response generateToken(Login login);
    Response generateNewToken(Login login);
}
