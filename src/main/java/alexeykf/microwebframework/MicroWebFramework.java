package alexeykf.microwebframework;

public class MicroWebFramework {

    public Response handle(Request request) {
        return Response.builder().status(404).body("Not found").build();
    }
}
