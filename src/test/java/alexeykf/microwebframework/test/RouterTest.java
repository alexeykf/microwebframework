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
    Handler otherHandler;


    private final String rootPath = "/";

    @Before
    public void init() {
        router = new Router();
        handler = createHandler("testHandler");
        otherHandler = createHandler("testOtherHandler");
        methods = new HttpMethod[] {HttpMethod.GET, HttpMethod.PUT};
        router.addRoute(rootPath, methods, handler);
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

    public Handler createHandler(String methodName) {
        try {
            Method handlerMethod = this.getClass().getDeclaredMethod(methodName, Request.class);
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
        router.getHandler(rootPath, HttpMethod.POST);
    }

    @Test
    public void testMergeRoute() throws NotFoundRouteException {
        Route addingRoute = new Route(rootPath);
        addingRoute.addHandler(HttpMethod.PUT, otherHandler);

        router.addRoute(addingRoute);
        Handler putHandler = router.getHandler(rootPath, HttpMethod.PUT);
        assertEquals(otherHandler.getHandlerMethod(), putHandler.getHandlerMethod());

        Handler getHandler = router.getHandler(rootPath, HttpMethod.GET);
        assertEquals(handler.getHandlerMethod(), getHandler.getHandlerMethod());
    }

    @Test
    public void testAddingNewRoute() throws NotFoundRouteException {
        String testPath = "/test";
        Route addingRoute = new Route(testPath);
        addingRoute.addHandler(HttpMethod.DELETE, otherHandler);

        router.addRoute(addingRoute);

        Handler deleteHandler = router.getHandler(testPath, HttpMethod.DELETE);
        assertEquals(otherHandler.getHandlerMethod(), deleteHandler.getHandlerMethod());
    }

    private Response testHandler(Request req) {
        return null;
    }

    private Response testOtherHandler(Request req) {
        return null;
    }
}
