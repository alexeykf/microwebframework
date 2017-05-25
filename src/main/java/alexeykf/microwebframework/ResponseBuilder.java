package alexeykf.microwebframework;

public class ResponseBuilder {

    private int status = 200;
    private String body = null;

    public ResponseBuilder() {
    }

    public ResponseBuilder status(int status) {
        this.status = status;
        return this;
    }

    public ResponseBuilder body(String body) {
        this.body = body;
        return this;
    }

    public Response build() {
        Response response = new Response(status);
        if (body != null) {
            response.addBody(body);
        }
        return response;
    }
}
