package com.elearning.elearning.exam;

import com.elearning.elearning.speciality.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface ExamRepository extends JpaRepository<Exam, Long>, JpaSpecificationExecutor<Exam> {
    Exam findById(String id);
    List<Exam> findBySpeciality(Speciality Speciality);

}
