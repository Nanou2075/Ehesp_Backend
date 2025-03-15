package com.elearning.elearning.degree;

import com.elearning.elearning.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree,String> {
     Optional<Degree> findByAccount(Account account);
     Optional<Degree> findByAccountId(String id);

}
