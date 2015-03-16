package Models;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class SurveyTest {

    @Test
    public void notNull(){

        Survey surv = new Survey();
        assertNotNull(surv);
    }

}