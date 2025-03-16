package com.elearning.elearning.module;

import com.elearning.elearning.teacher.Teacher;
import com.elearning.elearning.training.Training;
import com.elearning.elearning.video.Video;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleResponse {
    private String id;
    private String name;
    private Training training;
    private int numberOfVideo;
    private int numberOfPdf;
    private int numberOfPodcast;
   private  Teacher teacher;



}
