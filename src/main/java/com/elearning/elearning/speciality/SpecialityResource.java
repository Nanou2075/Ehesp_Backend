package com.elearning.elearning.speciality;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.exception.Response.Response;
import com.elearning.elearning.level.Level;
import com.elearning.elearning.mention.Mention;
import org.springframework.web.bind.annotation.*;

@RequestMapping("speciality/")
public interface SpecialityResource {
    @PostMapping("save")
    Response addTraining(@RequestBody Speciality speciality);

    @PutMapping("{id}")
    Response updateTraining(@PathVariable String id ,@RequestBody Speciality speciality);

    @DeleteMapping("{id}")
    Response removeTraining(@PathVariable String id );

    @PostMapping("domain")
    Response getTrainingByDomain(@RequestBody Domain domain);

    @PostMapping("level")
    Response getTrainingByLevel(@RequestBody Level level);

    @PostMapping("mention")
    Response getTrainingByMention(@RequestBody Mention mention);

    @GetMapping("{id}")
    Response getTraining(@PathVariable String id );

    @GetMapping("all")
    Response getAllTraining();

    @GetMapping("statistical")
    Response getTrainingStatical();
}
