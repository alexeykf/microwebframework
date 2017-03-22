package alexeykf.microwebframework;

import java.lang.reflect.Method;

public class Handler {

    private final Method handlerMethod;
    private Class<?> returnType;

    public Handler(Method handlerMethod) {
        this.handlerMethod = handlerMethod;
        inspect();
    }

    private void inspect() {
        returnType = handlerMethod.getReturnType();
    }

    public Class<?> getReturnType() {
        return returnType;
    }
}
