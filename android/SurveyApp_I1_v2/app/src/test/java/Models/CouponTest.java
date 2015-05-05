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

        QuestionAnswer questAns = new QuestionAnswer();
        assertNotNull(questAns);
    }


}