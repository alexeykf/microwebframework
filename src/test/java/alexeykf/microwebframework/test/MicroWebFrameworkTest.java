package alexeykf.microwebframework.test;

import alexeykf.microwebframework.*;
import alexeykf.microwebframework.annotations.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MicroWebFrameworkTest {

    MicroWebFramework framework;
    Request request;

    @Before
    public void init() {
        createRequest();
        createFramework();
    }

    private void createRequest() {
        request = new Request("/", "GET");
    }

    private void createFramework() {
        framework = new MicroWebFramework();
    }

    @Test
    public void testHandle() {
        Response response = framework.handle(request);
        assertNotNull(response);
    }

    @Test
    public void test404() {
        framework.get("/", (req) -> Response.builder().build());
        Response response = framework.handle(request);
        assertEquals(200, response.getStatus());

        request = new Request("/notfound", "GET");
        response = framework.handle(request);
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testGetShortcut() {
        String path = "/testgetshortcut";
        String body = "TEST GET SHORTCUT";
        LHandler handler = (req) -> Response.builder().status(200).body(body).build();
        framework.get(path, handler);
        Request request = mock(Request.class);
        when(request.getMethod()).thenReturn(HttpMethod.GET);
        when(request.getUrl()).thenReturn(path);
        Response response = framework.handle(request);

        int actualStatus = response.getStatus();
        String actualBody = response.getBody();
        assertEquals(200, actualStatus);
        assertEquals(body, actualBody);
    }

    @Test
    public void testPostShortcut() {
        String path = "/testpostshortcut";
        framework.post(path, (req) -> Response.builder().status(201).build());

        Request request = mock(Request.class);
        when(request.getMethod()).thenReturn(HttpMethod.POST);
        when(request.getUrl()).thenReturn(path);

        Response response = framework.handle(request);
        assertEquals(201, response.getStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRegister() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        framework.register(TestClass.class);

        Response response = framework.handle(createRequestMock("/route/test", HttpMethod.GET));
        assertEquals(200, response.getStatus());

        response = framework.handle(createRequestMock("/route/test", HttpMethod.POST));
        assertEquals(201, response.getStatus());
    }

    @Test
    public void testRegisterObject() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        TestClass testInstance = new TestClass();
        framework.register(testInstance);

        Response response = framework.handle(createRequestMock("/route/test", HttpMethod.GET));
        assertEquals(200, response.getStatus());

        response = framework.handle(createRequestMock("/route/test", HttpMethod.POST));
        assertEquals(201, response.getStatus());
    }

    private Request createRequestMock(String url, HttpMethod method) {
        Request request = mock(Request.class);
        when(request.getUrl()).thenReturn(url);
        when(request.getMethod()).thenReturn(method);
        return request;
    }

    @alexeykf.microwebframework.annotations.Route(value = "/route")
    public static class TestClass {
        @alexeykf.microwebframework.annotations.Route(value = "/test", method = HttpMethod.GET)
        public Response testGet(Request request) {
            return Response.builder().status(200).build();
        }

        @alexeykf.microwebframework.annotations.Route(value = "/test", method = HttpMethod.POST)
        public Response testPost(Request request) {
            return Response.builder().status(201).build();
        }
    }

}
