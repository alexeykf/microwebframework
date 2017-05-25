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
        Arrays.asList(methods).stream()
                .map(m -> Route.createRoute(path, m, handler))
                .forEach(this::addRoute);
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
        if (!pathExists || !routes.get(path).methodSupports(httpMethod)) {
            throw new NotFoundRouteException();
        }
        return routes.get(path).getHandler(httpMethod);
   }

}
