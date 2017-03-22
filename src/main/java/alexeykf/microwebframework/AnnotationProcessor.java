package alexeykf.microwebframework;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import alexeykf.microwebframework.annotations.Route;

public class AnnotationProcessor {

    private Class<?> clazz;
    private final String className;

    public AnnotationProcessor(String className) {
        this.className = className;
    }

    public void process() throws ClassNotFoundException {
        clazz = Class.forName(className);
    }


    private static boolean routeAnnotation(Method method) {
        return method.isAnnotationPresent(Route.class);
    }

    private static boolean methodIsPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

}
