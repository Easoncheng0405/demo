package com.jlu.xudong.demo.repository;

import java.util.List;
import com.jlu.xudong.demo.model.History;

import com.jlu.xudong.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findByUser(User user);

}