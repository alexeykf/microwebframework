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

}
