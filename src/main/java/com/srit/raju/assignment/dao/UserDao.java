package com.srit.raju.assignment.dao;

import com.srit.raju.assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
