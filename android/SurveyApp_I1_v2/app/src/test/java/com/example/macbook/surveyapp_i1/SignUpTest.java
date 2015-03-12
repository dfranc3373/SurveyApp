package com.example.macbook.surveyapp_i1;

import junit.framework.Assert;

import org.junit.Test;

import Models.Coupon;

public class SignUpTest {

    @Test
    public void notNull(){
        Coupon coupon = new Coupon();
        Assert.assertNotNull(coupon);
    }

}