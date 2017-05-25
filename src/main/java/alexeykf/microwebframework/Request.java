package alexeykf.microwebframework;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private String url;
    private HttpMethod method;
    private Map<String, String[]> parameters;
    private Map<String, String> headers = new HashMap<>();
    private String body;

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
                .body(getReaderFromServletRequest(servletRequest))
                .build();
        return request;
    }

    private static BufferedReader getReaderFromServletRequest(HttpServletRequest servletRequest) {
        try {
            return servletRequest.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getParameter(String parameter) {
        return parameters.get(parameter);
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

        public RequestBuilder body(BufferedReader body) {
            if (body == null) {
                request.setBody(null);
            } else {
                request.setBody(body.lines().reduce("", String::concat));
            }
            return this;
        }

        public Request build() {
            return request;
        }
    }
}
