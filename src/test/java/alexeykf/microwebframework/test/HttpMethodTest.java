package alexeykf.microwebframework.test;

import alexeykf.microwebframework.HttpMethod;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class HttpMethodTest {

    Map<String, HttpMethod> cases;

    @Before
    public void init() {
        cases = new HashMap(){{
            put("GET", HttpMethod.GET);
            put("POST", HttpMethod.POST);
            put("JFFJFJ", HttpMethod.OTHER);
        }};
    }

    @Test
    public void testFromString() {
        cases.keySet().forEach(k -> {
            assertEquals(cases.get(k), HttpMethod.fromString(k));
        });
    }
}
