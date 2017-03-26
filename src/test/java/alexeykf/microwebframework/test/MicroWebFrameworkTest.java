package alexeykf.microwebframework.test;

import alexeykf.microwebframework.*;
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

}
