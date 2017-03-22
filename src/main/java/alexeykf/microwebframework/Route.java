package alexeykf.microwebframework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Route {

    private final String route;
    private final Map<HttpMethod, Method> handlers;

    public Route(String route) {
        handlers = new HashMap<>();
        this.route = route;
    }

    public void addHandler(HttpMethod httpMethod, Method handler) {
        handlers.put(httpMethod, handler);
    }

    public Method getHandler(HttpMethod httpMethod) {
        return handlers.get(httpMethod);
    }

    public boolean handlerExists(HttpMethod httpMethod) {
        return handlers.containsKey(httpMethod);
    }

}
