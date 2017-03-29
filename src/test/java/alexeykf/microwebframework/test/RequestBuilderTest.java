package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Request;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String[]> parameters = new HashMap<>();
        parameters.put("param1", new String[]{"value"});

        Map<String, String> headers = new HashMap<>();

        Request request = builder
                .method("GET")
                .url("/")
                .headers(headers)
                .parameters(parameters).build();
        assertEquals(expectedMethod, request.getMethod());
        assertEquals(expectedUrl, request.getUrl());
        assertEquals(parameters, request.getParameters());
        assertEquals(headers, request.getHeaders());
    }
}
