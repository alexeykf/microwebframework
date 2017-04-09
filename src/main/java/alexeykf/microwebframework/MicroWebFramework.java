package alexeykf.microwebframework;

public class MicroWebFramework {

    protected final Router router = new Router();

    public Response handle(Request request) {
        try {
            Handler handler = router.getHandler(request.getUrl(), request.getMethod());
            return handler.invoke(request);
        } catch (NotFoundRouteException e) {
            return Response.builder().status(404).body("Not found").build();
        }
    }

    public void get(String path, LHandler lHandler) {
        addRoute(path, lHandler, HttpMethod.GET);
    }

    public void post(String path, LHandler lHandler) {
        addRoute(path, lHandler, HttpMethod.POST);
    }

    private void addRoute(String path, LHandler lHandler, HttpMethod method) {
        Handler handler = new Handler(lHandler);
        router.addRoute(path, new HttpMethod[]{method}, handler);
    }

    public void register(Class<?> clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        AnnotationProcessor processor = new AnnotationProcessor(clazz.getName());
        processor.process();
        processor.getRoutes().forEach(router::addRoute);
    }

    public void register(Object object) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        AnnotationProcessor processor = new AnnotationProcessor(object);
        processor.process();
        processor.getRoutes().forEach(router::addRoute);

    }
}
