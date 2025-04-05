package com.elearning.elearning.admin;


import com.elearning.elearning.account.Account;
import com.elearning.elearning.account.AccountRepository;
import com.elearning.elearning.common.CommService;
import com.elearning.elearning.common.PageResponse;
import com.elearning.elearning.exception.AlreadyExistException;
import com.elearning.elearning.exception.NotFoundException;
import com.elearning.elearning.exception.enums.Permission;
import com.elearning.elearning.i18n.LocalService;
import com.elearning.elearning.verification.VerificationService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.elearning.elearning.exception.Response.Security.NO;
import static com.elearning.elearning.exception.Response.Security.T;
import static com.elearning.elearning.messages.AccountMessage.*;


@Transactional
@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final LocalService localService;
    private final ModelMapper modelMapper;
    private final CommService commService;
    private final VerificationService verificationService;



    /**
     * @param request is the body of My addPartner Request
     */
    @Override
    public void addAdmin( AdminRequest request) throws MessagingException {
        /**
         *  check Admin to save ;
         */
        Optional.ofNullable(accountRepository.findByPhoneOrMailIgnoreCase(request.getPhone(), request.getMail())).ifPresent(
                (accountEntity) -> {
                    throw new AlreadyExistException(localService.getMessage(USER_EXIT));});
        String password = commService.password();
        Admin admin = modelMapper.map(request, Admin.class);
        Account account = modelMapper.map(admin, Account.class);
        account.setPassword(passwordEncoder.encode(password));
        account.setPermission(Permission.ADMIN);
        account.setAvailable(true);
        account.setAttempt(T);
        account.setNotLocked(true);
        account.setAttemptDateExp(Instant.now());
        account.setActivated(true);
        accountRepository.save(account);
        adminRepository.save(admin);
        verificationService.verificationCode(account,password);
    }




    /**
     *
     * @param id is the param for our request to get Admin
     * @return partner is the value return
     */

    @Override
    public Admin getAdminById(String id) {
        return adminRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)));
    }


    /**
     * @param id is the param for our request to get Admin
     * @param adminUpdate the new data for update the existingAdmin
     */
    @Override
    public void updateAdmin(String id, AdminUpdate adminUpdate) {
        adminRepository.findById(id)
                .map(existingAdminEntity -> updateExistingAdmin(existingAdminEntity, adminUpdate))
                .map(adminRepository::save)
                .orElseThrow(() -> new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND)));
    }


    /**
     *
     * @param existingAdminEntity is the value will be updated by new data
     * @param adminUpdate the new data for update the existingAdminEntity
     * @return the partner is the return value
     */

    public Admin updateExistingAdmin(Admin existingAdminEntity, AdminUpdate adminUpdate) {
        existingAdminEntity.setMail(adminUpdate.getMail());
        existingAdminEntity.setPhone(adminUpdate.getPhone());
        existingAdminEntity.setFullName(adminUpdate.getFullName());
        return existingAdminEntity;
    }

    /**
     *
     * @param id  is the param for our request to get Admin for delete
     */
    @Override
    public void deleteAdminById(String id) {
        adminRepository.findById(id)
                .ifPresentOrElse(adminRepository::delete, () -> {
                    throw new NotFoundException(NO, localService.getMessage(USER_NOT_FOUND));
                });

    }

    /**
     *
     * @param page the number of page
     * @param size the number per page
     * @return PageResponse is value i will return content size value of page number
     */

    @Override
    public PageResponse getAllAdmin(int page, int size) {
        List<Admin> all = adminRepository.findAllByAvailableTrue();
        if(all.isEmpty()){
            throw new NotFoundException(NO, localService.getMessage(ACCOUNT_EMPTY));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").ascending());
        Page<Admin> partners = adminRepository.findAllByAvailableTrue(pageable);
        return PageResponse.builder()
                .content(Arrays.asList(all.toArray()))
                .number(partners.getNumber())
                .size(partners.getSize())
                .totalElements(partners.getTotalElements())
                .totalPages(partners.getTotalPages())
                .first(partners.isFirst())
                .last(partners.isLast())
                .build();
    }




}


