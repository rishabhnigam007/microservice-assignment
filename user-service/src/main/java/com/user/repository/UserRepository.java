package com.user.repository;

import com.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select count(*) from user u where u.officeid=:officeID", nativeQuery = true)
    int existsByOfficeID(@Param("officeID") String officeID);

    @Query(value = "select * from user u where u.projectid=:projectID", nativeQuery = true)
    List<User> allUsersBySameProjectID(long projectID);
}