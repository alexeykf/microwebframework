package alexeykf.microwebframework.test;

import alexeykf.microwebframework.Handler;
import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Request;
import alexeykf.microwebframework.Response;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

public class HandlerTest {

    Method testHandlerMethod;
    Handler handler;

    Class<?> stringClass;
    Class<?> intClass;

    @Before
    public void init() throws NoSuchMethodException, ClassNotFoundException {
        stringClass = Class.forName("java.lang.String");
        intClass = int.class;

        testHandlerMethod = getClass().getDeclaredMethod("testHandlerMethod", stringClass, intClass);
        handler = new Handler(this, testHandlerMethod);
    }

    @Test
    public void testReturnType() throws ClassNotFoundException {
        Class<?> actualReturnType = handler.getReturnType();
        assertEquals(stringClass, actualReturnType);
    }

    public String testHandlerMethod(String a, int b) {
        return "test";
    }

    public Response testHandlerMethod2(Request req) {
        return Response.builder().build();
    }

    @Test
    public void testHandlerInvoke() throws NoSuchMethodException {
        Request request = mock(Request.class);
        handler = new Handler(this, getClass().getMethod("testHandlerMethod2", Request.class));
        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.getUrl()).thenReturn("/");
        handler.invoke(request);
    }
}
