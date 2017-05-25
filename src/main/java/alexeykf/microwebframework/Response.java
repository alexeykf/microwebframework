package alexeykf.microwebframework;

public class Response {

    private int status;
    private StringBuffer body;

    public Response(int status) {
       this.status = status;
    }

    public Response() {
        this(200);
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public void addBody(String body) {
        if (body == null) {
            return;
        }

        if (this.body == null) {
            this.body = new StringBuffer();
        }
        this.body.append(body);
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        if (body == null) {
            return null;
        }
        return body.toString();
    }
}
