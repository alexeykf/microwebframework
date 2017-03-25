package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Route;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class RouteTest {

    Route route;
    Method handler;

    @Before
    public void init() {
        route = new Route("/");
        handler = findHandler();
    }

    private Method findHandler() {
        try {
            return getClass().getDeclaredMethod("handler", new Class[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testAddHandlers() {
        addHandlers();
    }

    @Test
    public void testHandlersIsExist() {
        addHandlers();
        assertTrue(route.handlerExists(HttpMethod.GET));
        assertTrue(route.handlerExists(HttpMethod.POST));
        assertFalse(route.handlerExists(HttpMethod.PUT));
    }

    @Test
    public void testGetHandler() {
        addHandlers();

        Method actualHandler = route.getHandler(HttpMethod.GET);
        assertEquals(this.handler, actualHandler);

        actualHandler = route.getHandler(HttpMethod.POST);
        assertEquals(this.handler, actualHandler);

        actualHandler = route.getHandler(HttpMethod.DELETE);
        assertNull(actualHandler);

    }

    public void addHandlers() {
        route.addHandler(HttpMethod.GET, handler);
        route.addHandler(HttpMethod.GET, handler);
        route.addHandler(HttpMethod.POST, handler);
    }

    public String handler() {
        return "helloworld";
    }
}
