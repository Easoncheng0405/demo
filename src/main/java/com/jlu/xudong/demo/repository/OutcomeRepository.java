package com.jlu.xudong.demo.repository;

import java.util.List;
import com.jlu.xudong.demo.model.Outcome;

import com.jlu.xudong.demo.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

    List<Outcome> findByUser(User user);
}