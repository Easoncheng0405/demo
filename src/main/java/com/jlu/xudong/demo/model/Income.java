package com.jlu.xudong.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String incomeType;

    public double cash;

    public Date dateIncome;

    @OneToOne
    public User user;
}