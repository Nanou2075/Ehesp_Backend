package com.elearning.elearning.level;

import com.elearning.elearning.exception.Response.Response;
import org.springframework.web.bind.annotation.*;

@RequestMapping("level/")
public interface LevelResource {
    @PostMapping("save")
    Response addTraining(@RequestBody Level level);

    @PutMapping("{id}")
    Response updateTraining(@PathVariable String id ,@RequestBody Level level);

    @DeleteMapping("{id}")
    Response removeTraining(@PathVariable String id );

    @GetMapping("{id}")
    Response getTraining(@PathVariable String id );

    @GetMapping("all")
    Response getAllTraining();
    @GetMapping("statistical")
    Response getTrainingStatical();
}
