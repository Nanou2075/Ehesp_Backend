package com.elearning.elearning.cover;

import com.elearning.elearning.account.Account;
import com.elearning.elearning.module.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoverRepository extends JpaRepository<Cover,String> {
     Optional<Cover> findByModule(Module module);
     Cover findCoverByModule(Module module);
     Optional<Cover> findByModuleId(String id);

}
