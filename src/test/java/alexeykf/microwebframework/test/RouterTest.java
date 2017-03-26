package alexeykf.microwebframework.test;

import alexeykf.microwebframework.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RouterTest {

    Router router;
    HttpMethod[] methods;
    Handler handler;

    @Before
    public void init() {
        router = new Router();
        handler = createHandler();
        methods = new HttpMethod[] {HttpMethod.GET};
        String path = "/";
        router.addRoute("/", methods, handler);
    }

    @Test
    public void testAddRoot() throws NoSuchMethodException, NotFoundRouteException, InvocationTargetException, IllegalAccessException {
        Router spyRouter = spy(router);
        spyRouter.addRoute("/", methods, handler);
        verify(spyRouter, times(1)).createRoute("/");

        Handler actual = spyRouter.getHandler("/", HttpMethod.GET);
        assertEquals(handler.getHandlerMethod(), actual.getHandlerMethod());

        Request mock = mock(Request.class);
        actual.invoke(mock);
    }

    public Handler createHandler() {
        try {
            Method handlerMethod = this.getClass().getDeclaredMethod("testHandler", Request.class);
            return new Handler(this, handlerMethod);
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

    private Response testHandler(Request req) {
        return null;
    }
}
