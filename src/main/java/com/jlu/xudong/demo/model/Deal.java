package com.jlu.xudong.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;

@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String type;

    public String typeDetail;

    public double cash;

    public boolean mark;

    public Date date;

    @OneToOne
    public User user;
}