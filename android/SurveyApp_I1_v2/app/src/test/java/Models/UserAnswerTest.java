package Models;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class UserAnswerTest {

    @Test
    public void notNull(){

        UserAnswer userAns = new UserAnswer();
        assertNotNull(userAns);
    }

}