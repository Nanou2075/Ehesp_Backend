package com.elearning.elearning.section;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface SectionService {
    Response save(SectionRequest request);

    Response update(String id, SectionRequest request);

    Response search(String keyword);

    Response delete(String id);

    Response get(String id);

    Response getAll();


}
