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
