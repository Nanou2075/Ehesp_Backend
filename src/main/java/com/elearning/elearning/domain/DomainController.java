package com.elearning.elearning.domain;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static com.elearning.elearning.exception.Response.Security.OK;

@RequiredArgsConstructor
@RestController
public class DomainController implements DomainResource {

    private final DomainService domainService;

    @Override
    public Response save(Domain domain) {
        return domainService.save(domain);
    }

    @Override
    public Response update(String id,Domain domain) {
        return domainService.update(id, domain);
    }

    @Override
    public Response get(String id) {
        return domainService.get(id);
    }

    @Override
    public Response getAll() {
        return domainService.getAll();
    }

    @Override
    public Response delete(String id) {
        return domainService.delete(id);
    }

    @Override
    public Response search(String key) {
        return domainService.search(key);
    }

    @Override
    public Response getDomainStatical() {
        return new Response(OK, domainService.getDomainStatical());
    }
}


