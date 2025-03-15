package com.elearning.elearning.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long>{
    Domain findByName(String name);
    @Query("select section from Domain  section where section.name= :param " )
    List<Domain> search (@Param("param") String keyword);
    Domain findById(String id);

}
