package com.elearning.elearning.teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    Teacher findByPhone(String phone);


    @Query("select teacher from Teacher teacher  where   teacher.fullName = :param  or   teacher.phone = :param" )
    Page<Teacher> search (@Param("param") String keyword, Pageable pageable);


    @Query("select teacher from Teacher  teacher where   teacher.fullName = :param  or   teacher.phone = :param" )
    List<Teacher> searchTeacher (@Param("param") String keyword);

    Teacher findById(String id);

}
