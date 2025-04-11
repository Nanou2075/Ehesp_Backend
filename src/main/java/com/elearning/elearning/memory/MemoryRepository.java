package com.elearning.elearning.memory;

import com.elearning.elearning.speciality.Speciality;
import com.elearning.elearning.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long>, JpaSpecificationExecutor<Memory> {
    Memory findById(String id);
    List<Memory> findByStudent(Student student);

    List<Memory> findByStudentSpeciality(Speciality speciality);
}
