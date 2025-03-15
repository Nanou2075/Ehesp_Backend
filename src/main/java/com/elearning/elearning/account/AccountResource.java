package com.elearning.elearning.account;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("account")
public interface AccountResource {
    @GetMapping("get-account/{accountId}")
    Account getAccount(@PathVariable String accountId);
    @GetMapping("get/{accountId}")
    AccountResponse getAccountResponse(@PathVariable String accountId);

    @GetMapping("get/permission")
    Response getPermission();

    @GetMapping("statistical")
    Response getStatistical();
}
