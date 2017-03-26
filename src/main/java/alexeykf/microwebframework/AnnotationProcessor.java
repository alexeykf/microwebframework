package alexeykf.microwebframework;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AnnotationProcessor {

    private Class<?> clazz;
    private final String className;
    private Object instance;
    private List<Route> routes;

    public AnnotationProcessor(String className) {
        this.className = className;
    }

    public void process() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        clazz = Class.forName(className);
        instance = clazz.newInstance();
        findRoutes();
    }

    private void findRoutes() {
        routes = Arrays.asList(clazz.getDeclaredMethods()).stream()
                .filter(AnnotationProcessor::methodIsPublic)
                .filter(AnnotationProcessor::methodIsNotStatic)
                .filter(AnnotationProcessor::routeAnnotation)
                .map(m -> {
                    alexeykf.microwebframework.annotations.Route annotation = m.getAnnotation(alexeykf.microwebframework.annotations.Route.class);
                    String path = annotation.value();
                    HttpMethod method = annotation.method();
                    Route route = new Route(path);
                    Handler handler = new Handler(instance, m);
                    route.addHandler(method, handler);
                    return route;
                }).collect(Collectors.toList());
    }


    public static boolean routeAnnotation(Method method) {
        return method.isAnnotationPresent(alexeykf.microwebframework.annotations.Route.class);
    }

    public static boolean methodIsPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    public static boolean methodIsNotStatic(Method method) {
        return !Modifier.isStatic(method.getModifiers());
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
