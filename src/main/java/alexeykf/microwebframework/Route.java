package alexeykf.microwebframework;

import java.util.HashMap;
import java.util.Map;

public class Route {

    private final String route;
    private final Map<HttpMethod, Handler> handlers;

    public Route(String route) {
        handlers = new HashMap<>();
        this.route = route;
    }

    public void addHandler(HttpMethod httpMethod, Handler handler) {
        handlers.put(httpMethod, handler);
    }

    public Handler getHandler(HttpMethod httpMethod) {
        return handlers.get(httpMethod);
    }

    public boolean handlerExists(HttpMethod httpMethod) {
        return handlers.containsKey(httpMethod);
    }

}
