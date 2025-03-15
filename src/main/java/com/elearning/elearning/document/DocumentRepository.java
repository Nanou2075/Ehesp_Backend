package com.elearning.elearning.document;

import com.elearning.elearning.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,String> {
     Optional<Document> findByAccount(Account account);
     Optional<Document> findByAccountId(String id);

}
