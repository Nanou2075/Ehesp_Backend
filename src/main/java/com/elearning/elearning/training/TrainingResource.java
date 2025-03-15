package com.elearning.elearning.training;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.exception.Response.Response;
import org.springframework.web.bind.annotation.*;

@RequestMapping("training/")
public interface TrainingResource {
    @PostMapping("save")
    Response addTraining(@RequestBody Training training);

    @PutMapping("{id}")
    Response updateTraining(@PathVariable String id ,@RequestBody Training training);

    @DeleteMapping("{id}")
    Response removeTraining(@PathVariable String id );

    @PostMapping("domain")
    Response getTrainingByDomain(@RequestBody Domain domain);

    @GetMapping("{id}")
    Response getTraining(@PathVariable String id );

    @GetMapping("all")
    Response getAllTraining();
}
