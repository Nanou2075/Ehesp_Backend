package com.elearning.elearning.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,String> {
    List<Admin> findAllByAvailableTrue();
    Page<Admin> findAllByAvailableTrue(Pageable pageable);

}
