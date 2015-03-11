package Models;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class QuestionTest {

    @Test
    public void notNull(){

        Question quest = new Question();
        assertNotNull(quest);
    }

}