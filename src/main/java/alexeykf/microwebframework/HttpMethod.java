package alexeykf.microwebframework;

import java.util.Arrays;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    OTHER("OTHER");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return method;
    }

    public static HttpMethod fromString(String method) {
        return Arrays.stream(HttpMethod.values())
                .filter(h -> h.toString().equals(method))
                .findFirst()
                .orElse(OTHER);
    }
}
