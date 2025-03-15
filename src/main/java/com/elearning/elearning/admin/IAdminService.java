package com.elearning.elearning.admin;


import com.elearning.elearning.common.PageResponse;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public interface IAdminService {


    void addAdmin( AdminRequest request) throws MessagingException;

    Admin getAdminById(String id);

    void updateAdmin(String id, AdminUpdate adminUpdate);

    void deleteAdminById(String id);

    PageResponse getAllAdmin(int page, int size);
}
