package alexeykf.microwebframework.test;

import alexeykf.microwebframework.Handler;
import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.Route;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RouteTest {

    String rootPath = "/";
    Route route;
    Handler handler;

    @Before
    public void init() {
        route = new Route(rootPath);
        handler = findHandler();
    }

    private Handler findHandler() {
        try {
            Method method = getClass().getDeclaredMethod("handler", new Class[]{});
            return new Handler(this, method);
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
        assertTrue(route.methodSupports(HttpMethod.GET));
        assertTrue(route.methodSupports(HttpMethod.POST));
        assertFalse(route.methodSupports(HttpMethod.PUT));
    }

    @Test
    public void testGetHandler() {
        addHandlers();

        Handler actualHandler = route.getHandler(HttpMethod.GET);
        assertEquals(this.handler.getHandlerMethod(), actualHandler.getHandlerMethod());

        actualHandler = route.getHandler(HttpMethod.POST);
        assertEquals(this.handler.getHandlerMethod(), actualHandler.getHandlerMethod());

        actualHandler = route.getHandler(HttpMethod.DELETE);
        assertNull(actualHandler);

    }

    public void addHandlers() {
        route.addHandler(HttpMethod.GET, handler);
        route.addHandler(HttpMethod.GET, handler);
        route.addHandler(HttpMethod.POST, handler);
    }

    @Test
    public void testGettingMethods() {
        addHandlers();
        Set<HttpMethod> expectedSet = new HashSet(){{
            add(HttpMethod.GET);
            add(HttpMethod.POST);
        }};
        Set<HttpMethod> actualSet = route.methods();
        assertEquals(expectedSet, actualSet);
    }

    @Test
    public void testGettingRoute() {
        String actualRoute = route.getRoute();
        assertEquals(rootPath, actualRoute);
    }

    public String handler() {
        return "helloworld";
    }
}
