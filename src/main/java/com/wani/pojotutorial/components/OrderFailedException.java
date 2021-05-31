package com.wani.pojotutorial.components;

import javax.jms.JMSException;

public class OrderFailedException extends RuntimeException {

    public OrderFailedException(Exception e) {
        super(e);
    }
}
