package com.elearning.elearning.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    @Query("select account from Account account where account.mail = :param  or account.phone = :param" )
    Account findByUsernameIgnoreCase(@Param("param") String keyword);
    Account findAccountById(String id);
    Optional<Account> findByMailIgnoreCase(String mail);
    Account findByPhoneOrMailIgnoreCase(String phone, String mail);

}
