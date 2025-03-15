package com.elearning.elearning.account;


import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.security.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.messages.AccountMessage.USER_NOT_FOUND;


@RestController
@RequiredArgsConstructor
public class AccountController implements AccountResource{
    private final AccountRepository accountRepository;
    private final LocalService localService;
    private final AuthenticationService authenticationService;





    @Override
    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId) .orElseThrow(
                ()-> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)));
    }




    @Override
    public AccountResponse getAccountResponse (String accountId) {
        return getAccountResponse(accountRepository.findById(accountId) .orElseThrow(
                ()-> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)))) ;
    }

    private AccountResponse getAccountResponse(Account account) {
        return AccountResponse.builder()
                .phone(account.getPhone())
                .mail(account.getMail())
                .fullName(account.getFullName())
                .build();

    }







}
