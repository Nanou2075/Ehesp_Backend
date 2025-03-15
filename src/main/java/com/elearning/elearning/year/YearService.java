package com.elearning.elearning.year;


import com.elearning.elearning.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface YearService {
    Response save(YearRequest year);

    Response update(String id, YearRequest year);

    Response get(String id);

    Response getAll();

    Response delete(String id);

}
