package Models;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class CouponTest{

    @Before
    public void setup(){
        //do setup here
    }

    @Test
    public void notNull(){
        Coupon coupon = new Coupon();
        assertNotNull(coupon);
    }
}