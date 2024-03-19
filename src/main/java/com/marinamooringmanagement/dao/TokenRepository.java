package com.marinamooringmanagement.dao;

import com.marinamooringmanagement.model.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {
    TokenEntity findTokenEntityByToken(String token);
}
