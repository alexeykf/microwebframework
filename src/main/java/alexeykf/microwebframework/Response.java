package alexeykf.microwebframework;

public class Response {

    private int status;

    public Response(int status) {
       this.status = status;
    }

    public Response() {
        this(200);
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public int getStatus() {
        return status;
    }
}
