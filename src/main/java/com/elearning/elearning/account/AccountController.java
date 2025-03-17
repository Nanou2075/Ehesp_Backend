package com.elearning.elearning.account;


import com.elearning.elearning.admin.Admin;
import com.elearning.elearning.admin.AdminRepository;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.exception.enums.Permission;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.security.authentication.AuthenticationService;
import com.elearning.elearning.student.Student;
import com.elearning.elearning.student.StudentRepository;
import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.AccountMessage.USER_NOT_FOUND;


@RestController
@RequiredArgsConstructor
public class AccountController implements AccountResource{
    private final AccountRepository accountRepository;
    private final LocalService localService;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;





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

    @Override
    public Response getPermission() {
        return new Response(OK, Permission.values());
    }

    @Override
    public Response getStatistical() {
        Map<String, Object> values = new HashMap<>();
        values.put("user", adminRepository.findAll().size());
        values.put("teacher", teacherRepository.findAll().size());
        values.put("student", studentRepository.findAllByAvailableTrue().size());
        values.put("all", studentRepository.findAllByAvailableTrue().size()+teacherRepository.findAll().size()+studentRepository.findAll().size());
        return new Response(OK,values);
    }




    private AccountResponse getAccountResponse(Account account) {
        return AccountResponse.builder()
                .phone(account.getPhone())
                .mail(account.getMail())
                .fullName(account.getFullName())
                .build();

    }







}
