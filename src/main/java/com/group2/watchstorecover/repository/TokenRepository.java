package com.group2.watchstorecover.repository;

import com.group2.watchstorecover.entity.Account;
import com.group2.watchstorecover.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    boolean existsByTokenContentAndAccount(String token, Account account);
}
