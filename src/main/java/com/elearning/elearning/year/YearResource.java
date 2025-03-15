package com.elearning.elearning.year;


import com.elearning.elearning.exception.Response.Response;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping("year/")

public interface YearResource {
    @PostMapping("save")
    Response save(@RequestBody @Valid YearRequest year);

    @GetMapping("liste")
    Response list();

    @GetMapping("{id}")
    Response getById(@PathVariable String id);

    @PutMapping("update/{id}")
    Response update(@PathVariable String id, @Valid @RequestBody YearRequest year);

    @DeleteMapping("delete/{id}")
    Response delete(@PathVariable String id);
}
