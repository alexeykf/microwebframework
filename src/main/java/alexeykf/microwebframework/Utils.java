package alexeykf.microwebframework;

import java.lang.reflect.Method;

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

}
