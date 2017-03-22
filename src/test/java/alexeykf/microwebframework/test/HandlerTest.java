package alexeykf.microwebframework.test;

import alexeykf.microwebframework.Handler;
import org.junit.Before;
import org.junit.Test;

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
        handler = new Handler(testHandlerMethod);
    }

    @Test
    public void testReturnType() throws ClassNotFoundException {
        Class<?> actualReturnType = handler.getReturnType();
        assertEquals(stringClass, actualReturnType);
    }

    public String testHandlerMethod(String a, int b) {
        return "test";
    }
}
