package alexeykf.microwebframework.test;

import alexeykf.microwebframework.Response;
import alexeykf.microwebframework.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResponseBuilderTest {

    ResponseBuilder builder;

    @Before
    public void init() {
        createBuilder();
    }

    private void createBuilder() {
        builder = Response.builder();
    }

    @Test
    public void testDefaultStatus() {
        Integer expected = 200;
        Response response = builder.build();
        Integer actual = response.getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void testStatus() {
        Integer expected = 201;
        Response response = builder.status(201).build();
        Integer actual = response.getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void testBody() {
        String expected = "helloworld";
        Response response = builder.body("helloworld").build();
        String actual = response.getBody();
        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyBody() {
        assertNull(builder.build().getBody());
    }
}
