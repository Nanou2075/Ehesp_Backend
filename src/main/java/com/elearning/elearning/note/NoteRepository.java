package com.elearning.elearning.note;

import com.elearning.elearning.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>, JpaSpecificationExecutor<Note> {
    Note findById(String id);
    List<Note> findByStudent(Student student);

}
