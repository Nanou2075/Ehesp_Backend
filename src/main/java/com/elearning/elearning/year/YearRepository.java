package com.elearning.elearning.year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YearRepository extends JpaRepository<Year, String>, JpaSpecificationExecutor<Year> {
    Year findByBeginYear(String beginYear);

    Year findByEndYear(String endYear);

    Year findByEnabled(Boolean enabled);


}
