package alexeykf.microwebframework;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public final class Utils {

    private Utils() {
    }

    public static Method getHandleMethod(LHandler lHandler) {
        try {
            return lHandler.getClass().getDeclaredMethod("handle", new Class[]{Request.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String normalizePath(String path) {

        if (path.equals("")) {
            return "/";
        }

        if (!path.startsWith("/")) {
            path = "/"  + path;
        }

        if (path.endsWith("/") && path.length() > 1) {
            path = path.substring(0, path.length() - 1);
        }

        path = path.replaceAll("/{2,}", "/");
        return path;
    }

    public static Map<String, String> servletHeadersToMap(HttpServletRequest req) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            String headerValue= req.getHeader(header);
            headers.put(header, headerValue);
        }
        return headers;
    }

}
