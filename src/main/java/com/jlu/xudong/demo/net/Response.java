package com.jlu.xudong.demo.net;

/**
 * Response
 */
public class Response<Body> {

    public int status = 200;

    public String msg = "request success.";

    public Body body;

    public long st = System.currentTimeMillis();
}