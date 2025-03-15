package com.elearning.elearning.admin;

import com.elearning.elearning.exception.Response.Response;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin/")
public interface IAdminResource {

    @PostMapping("save")
    Response addAdmin (@RequestBody AdminRequest agency) throws MessagingException;

    @GetMapping("get/{id}")
    Response getAdminById(@PathVariable  String id);

    @DeleteMapping("delete/{id}")
    Response deleteAdminById (@PathVariable String id);

    @PutMapping("update/{id}")
    Response updateAdmin (@PathVariable String id,@RequestBody AdminUpdate request);

    @GetMapping("all")
    Response getAllAdmin(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                    @RequestParam(name = "size", defaultValue = "50", required = false) int size);

}
