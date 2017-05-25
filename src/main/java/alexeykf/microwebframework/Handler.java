package alexeykf.microwebframework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Handler {

    private final Object obj;
    private final Method handlerMethod;
    private Class<?> returnType;

    public Handler(Object obj, Method handlerMethod) {
        this.obj = obj;
        this.handlerMethod = handlerMethod;
        inspect();
    }

    public Handler(LHandler obj) {
        this(obj, Utils.getHandleMethod(obj));
    }

    public Method getHandlerMethod() {
        return handlerMethod;
    }

    public Response invoke(Request request) {
        try {
            handlerMethod.setAccessible(true);
            return (Response) handlerMethod.invoke(obj, request);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Response.builder().status(500).build();
    }

    private void inspect() {
        returnType = handlerMethod.getReturnType();
    }

    public Class<?> getReturnType() {
        return returnType;
    }
}
