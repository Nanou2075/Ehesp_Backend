package com.elearning.elearning.domain;

import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.level.Level;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public interface DomainService {
    Response save(Domain domain);

    Response update(String id, Domain domain);

    Response search(String keyword);

    Response delete(String id);

    Response get(String id);

    Set<DomainResponse> getAllByLevel(Level level);

    Set<DomainValue> getDomainStatical();

    Response getAll();


}
