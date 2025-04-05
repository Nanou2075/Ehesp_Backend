package com.elearning.elearning.appreciation;

import com.elearning.elearning.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AppreciationRepository extends JpaRepository<Appreciation, Long>, JpaSpecificationExecutor<Appreciation> {
    Appreciation findById(String id);
    List<Appreciation> findByStudent(Student student);

}
