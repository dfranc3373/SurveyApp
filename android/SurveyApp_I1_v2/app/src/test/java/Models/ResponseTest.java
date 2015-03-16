package Models;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class ResponseTest {

    @Test
    public void notNull(){

        Response resp = new Response();
        assertNotNull(resp);
    }

}