package alexeykf.microwebframework;

public class ResponseBuilder {

    private int status = 200;

    public ResponseBuilder() {
    }

    public ResponseBuilder status(int status) {
        this.status = status;
        return this;
    }

    public Response build() {
        return new Response(status);
    }
}
