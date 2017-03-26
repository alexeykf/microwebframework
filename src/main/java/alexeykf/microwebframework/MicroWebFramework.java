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
        router.addRoute(path, new HttpMethod[] {HttpMethod.GET}, new Handler(lHandler, Utils.getHandleMethod(lHandler)));
    }
}
