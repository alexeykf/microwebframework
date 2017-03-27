package alexeykf.microwebframework.annotations;

import alexeykf.microwebframework.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Route {
    String value();
    HttpMethod method() default HttpMethod.GET;
}
