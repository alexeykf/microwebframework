package alexeykf.microwebframework.test;

import alexeykf.microwebframework.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResponseTest {

    Response response;
    final int defaultStatus = 200;

    @Before
    public void init() {
        response = new Response();
    }

    @Test
    public void testDefaultStatus() {
        int actualStatus = response.getStatus();
        assertEquals(defaultStatus, actualStatus);
    }

    @Test
    public void testAddBody() {
        response.addBody("test");
        String actualBody = response.getBody();
        assertEquals("test", actualBody);
    }

    @Test
    public void testAddNullBody() {
        response.addBody(null);
        System.out.print(response.getBody());
        assertNull(response.getBody());
    }
}
