package com.jlu.xudong.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;

@Entity
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String outcomeType;

    public double cash;

    public Date dateOutcome;

    @OneToOne
    public User user;
}