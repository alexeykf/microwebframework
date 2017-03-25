package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Request;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

public class RequestTest {

    HttpServletRequest servletRequest;
    Request request;


    @Before
    public void init() {
        createHttpServletRequestMock();
        createRequest();
    }

    private void createHttpServletRequestMock() {
        servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn("GET");
        when(servletRequest.getRequestURI()).thenReturn("/");
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
}
