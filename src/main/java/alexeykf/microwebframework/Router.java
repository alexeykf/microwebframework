package alexeykf.microwebframework;

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

    public void addRoute(Route route) {
        String url = route.getRoute();
        if (routes.containsKey(url)) {
            Route existingRoute = routes.get(url);
            mergeRoute(existingRoute, route);
        } else {
            routes.put(route.getRoute(), route);
        }
    }

    private void mergeRoute(Route existingRoute, Route addingRoute) {
        addingRoute.methods().stream()
                .forEach(m -> existingRoute.addHandler(m, addingRoute.getHandler(m)));
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
