package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class RequestBuilderTest {

    Request.RequestBuilder builder;
    BufferedReader body;
    String[] testBody = new String[]{"test ", "body"};

    @Before
    public void init() {
        builder = new Request.RequestBuilder();

        body = mock(BufferedReader.class);
        when(body.lines()).thenReturn(Stream.of(testBody));
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
                .body(body)
                .parameters(parameters).build();
        assertEquals(expectedMethod, request.getMethod());
        assertEquals(expectedUrl, request.getUrl());
        assertEquals(parameters, request.getParameters());
        assertEquals(headers, request.getHeaders());
        assertEquals("test body", request.getBody());
    }

    @Test
    public void testNullBody() {
        Request request = builder.body(null).build();
        assertNull(request.getBody());
    }
}
