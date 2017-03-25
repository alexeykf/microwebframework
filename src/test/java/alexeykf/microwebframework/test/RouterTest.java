package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import alexeykf.microwebframework.NotFoundRouteException;
import alexeykf.microwebframework.Router;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.lang.reflect.Method;

public class RouterTest {

    Router router;
    HttpMethod[] methods;
    Method handler;

    @Before
    public void init() {
        router = new Router();
        handler = createHandler();
        methods = new HttpMethod[] {HttpMethod.GET};
        String path = "/";
        router.addRoute("/", methods, handler);
    }

    @Test
    public void testAddRoot() throws NoSuchMethodException, NotFoundRouteException {
        Router spyRouter = spy(router);
        spyRouter.addRoute("/", methods, handler);
        verify(spyRouter, times(1)).createRoute("/");

        Method actual = spyRouter.getHandler("/", HttpMethod.GET);
        assertEquals(handler, actual);
    }

    public Method createHandler() {
        try {
            Method handler = this.getClass().getDeclaredMethod("testHandler");
            return handler;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test(expected = NotFoundRouteException.class)
    public void testNotFoundPath() throws NotFoundRouteException {
        router.getHandler("/notfound", HttpMethod.GET);
    }

    @Test(expected = NotFoundRouteException.class)
    public void testNotFoundMethod() throws NotFoundRouteException {
        router.getHandler("/", HttpMethod.POST);
    }

    private void testHandler() {
    }
}
