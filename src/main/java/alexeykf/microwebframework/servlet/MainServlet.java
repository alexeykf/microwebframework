package alexeykf.microwebframework.servlet;

import alexeykf.microwebframework.MicroWebFramework;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    private MicroWebFramework framework;

    @Override
    public void init() {
        framework = new MicroWebFramework();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {

        String url = req.getRequestURI();
        String method = req.getMethod();

        try (PrintWriter writer = resp.getWriter()) {
            writer.println(String.format("url: %s", url));
            writer.println(String.format("method: %s", method));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
