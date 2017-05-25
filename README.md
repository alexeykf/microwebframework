## Игрушечный веб-фреймворк на java

### Пример использования:

```
public class Example extends MicroWebFramework {

    public Example() {
        init();
    }

    private void init() {
       get("/", req -> Response.builder().body("Helloworld").build());
       post("/echo", req -> Response.builder().body(req.getBody()).build());
    }
}
```

### Пример web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
    <servlet>
        <servlet-name>mainservlet</servlet-name>
        <servlet-class>alexeykf.microwebframework.servlet.MainServlet</servlet-class>
        <init-param>
            <param-name>MicroWebFrameworkClassName</param-name>
            <param-value>alexeykf.example.Example</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>mainservlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
</web-app>
```
