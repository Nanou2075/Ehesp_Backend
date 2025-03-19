package com.elearning.elearning.student;

import com.elearning.elearning.domain.Domain;
import com.elearning.elearning.speciality.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findStudentById(String id);
    Page<Student> findAllByAvailableTrue(Pageable pageable);
    List<Student> findAllByAvailableTrue();
    List<Student> findAllBySpeciality(Speciality speciality);
    List<Student> findAllBySpecialityMentionDomain(Domain domain);


}
