package com.jlu.xudong.demo.repository;

import java.util.List;
import com.jlu.xudong.demo.model.Deal;

import com.jlu.xudong.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DealRepository extends JpaRepository<Deal, Integer> {

    List<Deal> findByUser(User user);

}