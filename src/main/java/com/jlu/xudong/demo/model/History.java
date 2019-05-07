package com.jlu.xudong.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class History {
    @Id
    public int id;

    public String type;

    public double cash;

    public boolean mark;

    public Date date;

    public String form;

    @OneToOne
    public User user;
}