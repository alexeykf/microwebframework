package alexeykf.microwebframework.test;

import alexeykf.microwebframework.MicroWebFramework;
import alexeykf.microwebframework.Request;
import alexeykf.microwebframework.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

}
