package com.elearning.elearning.book;

import com.elearning.elearning.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BookRepository extends JpaRepository<Book,String> {
    Set<Book> findAllByModule(Module module);
    Set<Book> findAllByModuleId(String id);
}
