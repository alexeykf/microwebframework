package alexeykf.microwebframework.test;

import alexeykf.microwebframework.AnnotationProcessor;
import alexeykf.microwebframework.Response;
import alexeykf.microwebframework.annotations.Route;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

public class AnnotationProcessorTest {

    Method route;
    Method withoutAnnotation;
    Method privateMethod;
    AnnotationProcessor processor;

    @Before
    public void init() throws NoSuchMethodException {
        route = getDeclaredMethod("route");
        withoutAnnotation = getDeclaredMethod("withoutAnnotation");
        privateMethod = getDeclaredMethod("privateMethod");
        String testClass = getClass().getName() + ".TestClass";
        processor = new AnnotationProcessor(getClass().getDeclaredClasses()[0].getName());
    }

    public Method getDeclaredMethod(String name) throws NoSuchMethodException {
        return TestClass.class.getDeclaredMethod(name);
    }

    @Test
    public void testRouteAnnotation() {
        assertTrue(AnnotationProcessor.routeAnnotation(route));
        assertFalse(AnnotationProcessor.routeAnnotation(withoutAnnotation));
    }

    @Test
    public void testMethodIsPublic() {
        assertFalse(AnnotationProcessor.methodIsPublic(privateMethod));
        assertTrue(AnnotationProcessor.methodIsPublic(route));
    }

    @Test
    public void testProcess() throws ClassNotFoundException {
        processor.process();
    }

    public static class TestClass {

        @Route("/")
        public Response route() {
            return null;
        }

        public Response withoutAnnotation() {
            return null;
        }

        private void privateMethod() {
        }
    }
}
