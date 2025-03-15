package com.elearning.elearning.year;


import com.elearning.elearning.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class YearController implements YearResource {

    private final YearService yearService;

    @Override
    public Response save(YearRequest year) {
        return yearService.save(year);
    }

    @Override
    public Response list() {
        return yearService.getAll();
    }

    @Override

    public Response getById(String id) {
        return yearService.get(id);
    }

    @Override
    public Response update(String id, YearRequest year) {
        return yearService.update(id, year);
    }

    @Override
    public Response delete(String id) {
        return yearService.delete(id);
    }
}
