package alexeykf.microwebframework;

@FunctionalInterface
public interface LHandler {
    Response handle(Request req);
}
