package com.elearning.elearning.admin;


import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.i18n.LocalService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static com.elearning.elearning.exception.Response.Security.OK;
import static com.elearning.elearning.messages.AccountMessage.*;


@RestController
@RequiredArgsConstructor
public class AdminController implements IAdminResource {
    private final AdminService adminService;
    private final LocalService localService;

    @Override
    public Response addAdmin(AdminRequest agency) throws MessagingException {
        adminService.addAdmin(agency);
        return new Response(OK,localService.getMessage(ACCOUNT_SAVE));
    }

    @Override
    public Response getAdminById(String id) {
        return new Response(OK,adminService.getAdminById(id));
    }

    @Override
    public Response deleteAdminById(String id) {
        adminService.deleteAdminById(id);
        return  new Response(OK,localService.getMessage(DELETE));
    }

    @Override
    public Response updateAdmin (String id, AdminUpdate request) {
        adminService.updateAdmin(id,request);
        return new Response(OK,localService.getMessage(UPDATE));
    }

    @Override
    public Response getAllAdmin(int page, int size) {
        return new Response(OK,adminService.getAllAdmin(page,size));
    }
}

