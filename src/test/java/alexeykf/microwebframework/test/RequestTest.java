package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Request;
import alexeykf.microwebframework.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class RequestTest {

    HttpServletRequest servletRequest;
    Request request;


    @Before
    public void init() {
        createHttpServletRequestMock("GET");
        createRequest();
    }

    private void createHttpServletRequestMock(String method) {
        servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(method);
        when(servletRequest.getRequestURI()).thenReturn("/");

        List<String> headersList = new ArrayList() {{
            add("host");
            add("connection");
        }};
        Enumeration<String> headers = new Vector<>(headersList).elements();
        when(servletRequest.getHeaderNames()).thenReturn(headers);
        when(servletRequest.getHeader("host")).thenReturn("localhost");
        when(servletRequest.getHeader("connection")).thenReturn("keep-alive");
    }

    private void createRequest() {
        request = Request.createRequest(servletRequest);
    }


    @Test
    public void testMethod() {
        HttpMethod expectedMethod = HttpMethod.GET;
        HttpMethod actualMethod = request.getMethod();
        assertEquals(expectedMethod, actualMethod);
    }


    @Test
    public void testUrl() {
        String expectedUrl = "/";
        String actualUrl = request.getUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testParameter() {
        Map<String, String[]> parameters = new HashMap() {{
            put("test", new String[]{"testvalue"});
        }};
        request = new Request.RequestBuilder().method("GET").url("/").parameters(parameters).build();
        assertEquals("testvalue", request.getParameter("test")[0]);
        assertNull(request.getParameter("sdfadsf"));
    }

    @Test
    public void testSetMethod() {
        final Map<String, HttpMethod> methods = new HashMap() {{
            put("GET", HttpMethod.GET);
            put("POST", HttpMethod.POST);
            put("PUT", HttpMethod.PUT);
            put("DELETE", HttpMethod.DELETE);
            put("PATCH", HttpMethod.OTHER);
        }};

        methods.keySet().forEach(k -> {
            createHttpServletRequestMock(k);
            assertEquals(methods.get(k), Request.createRequest(servletRequest).getMethod());
        });
    }

    @Test
    public void testHeaders() {
        assertEquals("keep-alive", request.getHeader("connection"));
        assertEquals("localhost", request.getHeader("host"));
    }

    @Test
    public void testEmptyHeaders() {
        Request request = new Request.RequestBuilder().url("/").method("GET").build();
        assertNotNull(request.getHeaders());
    }

    @Test
    public void testIOExceptionInBR() throws IOException {
        when(servletRequest.getReader()).thenThrow(IOException.class);
        Request request = Request.createRequest(servletRequest);
        assertNull(request.getBody());
    }
}
