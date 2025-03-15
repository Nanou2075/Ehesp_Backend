package com.elearning.elearning.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Section> {
    Section findByName(String name);
    @Query("select section from Section  section where section.name= :param " )
    List<Section> search (@Param("param") String keyword);
    Section findById(String id);

}
