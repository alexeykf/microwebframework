package alexeykf.microwebframework;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private String url;
    private HttpMethod method;
    private Map<String, String[]> parameters;
    private Map<String, String> headers = new HashMap<>();

    private Request() {
    }


    public Request(String url, String method) {
        this.url = url;
        setMethod(method);
    }

    public String getUrl() {
        return url;
    }

    private void setMethod(String strMethod) {
        method = HttpMethod.fromString(strMethod.toUpperCase());
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public HashMap<String, String> getHeaders() {
        return new HashMap<>(headers);
    }

    public static Request createRequest(HttpServletRequest servletRequest) {
        Request request = new RequestBuilder()
                .method(servletRequest.getMethod())
                .url(servletRequest.getRequestURI())
                .headers(Utils.servletHeadersToMap(servletRequest))
                .parameters(servletRequest.getParameterMap())
                .build();
        return request;
    }

    public String[] getParameter(String parameter) {
        return parameters.get(parameter);
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public static class RequestBuilder {

        private Request request;


        public RequestBuilder() {
            request = new Request();

        }

        public RequestBuilder method(String method) { request.setMethod(method);
            return this;
        }

        public RequestBuilder url(String url) {
            request.url = Utils.normalizePath(url);
            return this;
        }

        public RequestBuilder parameters(Map<String, String[]> parameters) {
            request.parameters = parameters;
            return this;
        }

        public RequestBuilder headers(Map<String, String> headers) {
            request.headers = headers;
            return this;
        }

        public Request build() {
            return request;
        }
    }
}
