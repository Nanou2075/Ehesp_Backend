package com.elearning.elearning.section;

import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SectionController implements SectionResource {

    private final SectionService sectionService;

    @Override
    public Response save(SectionRequest request) {
        return sectionService.save(request);
    }

    @Override
    public Response update(String id, SectionRequest request) {
        return sectionService.update(id, request);
    }

    @Override
    public Response get(String id) {
        return sectionService.get(id);
    }

    @Override
    public Response getAll() {
        return sectionService.getAll();
    }

    @Override
    public Response delete(String id) {
        return sectionService.delete(id);
    }

    @Override
    public Response search(String key) {
        return sectionService.search(key);
    }
}


