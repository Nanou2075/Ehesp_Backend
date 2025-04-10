package com.elearning.elearning.module;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.level.Level;
import com.elearning.elearning.mention.Mention;
import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
    private Set<Speciality> speciality;
    private Mention mention;
    private Domain domain;
    private Level level;
    private int numberOfVideo;
    private int numberOfPdf;
    private int numberOfPodcast;
   private  Teacher teacher;



}
