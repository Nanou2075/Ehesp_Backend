package com.elearning.elearning.mention;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.level.Level;
import org.springframework.web.bind.annotation.*;

@RequestMapping("speciality/")
public interface MentionResource {
    @PostMapping("save")
    Response addTraining(@RequestBody Mention mention);

    @PutMapping("{id}")
    Response updateTraining(@PathVariable String id ,@RequestBody Mention mention);

    @DeleteMapping("{id}")
    Response removeTraining(@PathVariable String id );

    @PostMapping("domain")
    Response getMentionByDomain (@RequestBody Domain domain);

    @GetMapping("{id}")
    Response getTraining(@PathVariable String id );

    @GetMapping("all")
    Response getAllTraining();
    @GetMapping("statistical")
    Response getTrainingStatical();
}
