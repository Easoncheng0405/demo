package com.jlu.xudong.demo.repository;

import java.util.List;
import com.jlu.xudong.demo.model.Income;

import com.jlu.xudong.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findByUser(User user);

}