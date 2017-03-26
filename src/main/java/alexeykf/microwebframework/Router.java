package alexeykf.microwebframework;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, Route> routes;

    public Router() {
        routes = new HashMap<>();
    }

    public void addRoute(String path, HttpMethod[] methods, final Handler handler) {
        final Route route = createRoute(path);
        Arrays.asList(methods).forEach(m -> route.addHandler(m, handler));
        routes.put(path, route);
    }

    public Handler getHandler(String path, HttpMethod httpMethod) throws NotFoundRouteException {
        boolean pathExists = routes.containsKey(path);
        if (!pathExists || !routes.get(path).handlerExists(httpMethod)) {
            throw new NotFoundRouteException();
        }
        return routes.get(path).getHandler(httpMethod);
   }

    public Route createRoute(String token) {
        Route route = new Route(token);
        return route;
    }

}
