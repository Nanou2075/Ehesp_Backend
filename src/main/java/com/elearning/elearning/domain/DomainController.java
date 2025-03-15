package com.elearning.elearning.domain;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DomainController implements DomainResource {

    private final DomainService domainService;

    @Override
    public Response save(DomainRequest request) {
        return domainService.save(request);
    }

    @Override
    public Response update(String id, DomainRequest request) {
        return domainService.update(id, request);
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
}


