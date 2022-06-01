package com.springsecurity.repository;

import com.springsecurity.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query(value = "select * from login u where u.username=:username", nativeQuery = true)
    Login getUserByUserName(@Param("username") String username);
}