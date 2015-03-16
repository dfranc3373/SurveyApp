package Models;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class UserTest {

    @Test
    public void notNull(){

        User user = new User();
        assertNotNull(user);
    }

}