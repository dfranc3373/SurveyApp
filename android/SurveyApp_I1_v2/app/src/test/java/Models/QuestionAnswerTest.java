package Models;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class QuestionAnswerTest {

    @Test
    public void notNull(){

        QuestionAnswer questAns = new QuestionAnswer();
        assertNotNull(questAns);
    }

}