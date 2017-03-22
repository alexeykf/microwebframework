package alexeykf.microwebframework;

import javax.servlet.http.HttpServletRequest;

public class Request {

    private String url;
    private String method;


    public Request(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public static Request createRequest(HttpServletRequest servletRequest) {
        String method = servletRequest.getMethod();
        String url = servletRequest.getRequestURI();
        Request request = new Request(url, method);
        return request;
    }
}
