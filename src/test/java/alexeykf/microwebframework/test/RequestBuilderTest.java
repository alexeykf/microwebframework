package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Request;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestBuilderTest {

    Request.RequestBuilder builder;

    @Before
    public void init() {
        builder = new Request.RequestBuilder();
    }

    @Test
    public void test() {
        HttpMethod expectedMethod = HttpMethod.GET;
        String expectedUrl = "/";
        Request request = builder.method("GET").url("/").build();
        assertEquals(expectedMethod, request.getMethod());
        assertEquals(expectedUrl, request.getUrl());
    }
}
