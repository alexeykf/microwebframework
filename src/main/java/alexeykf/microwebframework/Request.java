package alexeykf.microwebframework;

import javax.servlet.http.HttpServletRequest;

public class Request {

    private String url;
    private HttpMethod method;


    public Request(String url, String method) {
        this.url = url;
        setMethod(method);
    }

    public String getUrl() {
        return url;
    }

    private void setMethod(String strMethod) {
        switch (strMethod.toUpperCase()) {
            case "GET":
                method = HttpMethod.GET;
                break;
            case "POST":
                method = HttpMethod.POST;
                break;
            case "PUT":
                method = HttpMethod.PUT;
                break;
            case "DELTE":
                method = HttpMethod.DELETE;
                break;
            default:
                method = HttpMethod.OTHER;
                break;
        }

    }

    public HttpMethod getMethod() {
        return method;
    }

    public static Request createRequest(HttpServletRequest servletRequest) {
        String method = servletRequest.getMethod();
        String url = servletRequest.getRequestURI();
        Request request = new Request(url, method);
        return request;
    }
}
