package alexeykf.microwebframework.test;

import alexeykf.microwebframework.Utils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class UtilsTest {

    Map<String, String> paths;

    @Before
    public void init() {
        paths = new HashMap() {{
            put("/", "/");
            put("", "/");
            put("/index/", "/index");
            put("/index", "/index");
            put("index", "/index");
            put("/index//test", "/index/test");
            put("/index////test/", "/index/test");
        }};

    }

    @Test
    public void testNormalizePath() {
        paths.keySet().forEach(p -> assertEquals(paths.get(p), Utils.normalizePath(p)));
    }

}
