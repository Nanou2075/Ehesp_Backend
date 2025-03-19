package com.elearning.elearning.module;

import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleResponse {
    private String id;
    private String name;
    private Speciality speciality;
    private int numberOfVideo;
    private int numberOfPdf;
    private int numberOfPodcast;
   private  Teacher teacher;



}
