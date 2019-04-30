package com.jlu.xudong.demo.repository;

import com.jlu.xudong.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findByNameAndPassword(String name, String password);
}