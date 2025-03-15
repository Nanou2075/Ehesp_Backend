package com.elearning.elearning.account;


import com.elearning.elearning.admin.Admin;
import com.elearning.elearning.admin.AdminRepository;
import com.elearning.elearning.common.InjectionService;
import com.elearning.elearning.exception.enums.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.elearning.elearning.account.AccountType.SUPERADMIN;


@RequiredArgsConstructor
@Component
public class AccountInitializer implements CommandLineRunner {

    private final InjectionService injectionService;


    @Override
    public void run(String... args) throws Exception {
        String encodePwd =injectionService.passwordEncoder.encode("nanouroukou@2075");
        Account account = injectionService.accountRepository.findByUsernameIgnoreCase("nanouroukou@gmail.com");
        if (account == null) {
             Admin admin = new Admin();
             admin.setPassword(encodePwd);
             admin.setMail("nanouroukou@gmail.com");
             admin.setPhone("76646465");
             admin.setFullName("Seydou Nanouroukourou Kone");
             admin.setAttempt(3);
             admin.setNotLocked(true);
             admin.setAttemptDateExp(Instant.now());
             admin.setActivated(true);
             admin.setPermission(Permission.SUPERADMIN);
             admin.setNotLocked(true);
            injectionService.accountRepository.save(admin);
            injectionService.adminRepository.save(admin);

        }
//        account.setPermission(Permission.SUPERADMIN);
//        injectionService.accountRepository.save(account);



    }
}
