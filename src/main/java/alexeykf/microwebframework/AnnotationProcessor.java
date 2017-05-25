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

    private String prefixPath = "";

    public AnnotationProcessor(String className) {
        this.className = className;
    }

    public AnnotationProcessor(Object object) {
        this(object.getClass().getName());
        instance = object;

    }

    public void process() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        clazz = Class.forName(className);
        if (instance == null) {
            instance = clazz.newInstance();
        }
        findRoutes();
    }

    private void findRoutes() {
        findPrefixPath();
        routes = Arrays.asList(clazz.getDeclaredMethods()).stream()
                .filter(AnnotationProcessor::methodIsPublic)
                .filter(AnnotationProcessor::methodIsNotStatic)
                .filter(AnnotationProcessor::routeAnnotation)
                .map(m -> {
                    alexeykf.microwebframework.annotations.Route routeAnnotation = m.getAnnotation(alexeykf.microwebframework.annotations.Route.class);
                    String path = String.format("%s%s", prefixPath, routeAnnotation.value());
                    HttpMethod method = routeAnnotation.method();
                    Route route = new Route(path);
                    Handler handler = new Handler(instance, m);
                    route.addHandler(method, handler);
                    return route;
                }).collect(Collectors.toList());
    }

    private void findPrefixPath() {
        boolean classHaveRouteAnnotation = clazz.isAnnotationPresent(alexeykf.microwebframework.annotations.Route.class);
        if (classHaveRouteAnnotation) {
            alexeykf.microwebframework.annotations.Route routeAnnotation = clazz.getDeclaredAnnotation(alexeykf.microwebframework.annotations.Route.class);
            prefixPath = routeAnnotation.value();
        }
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
