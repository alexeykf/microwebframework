package alexeykf.microwebframework.servlet;

import alexeykf.microwebframework.MicroWebFramework;
import alexeykf.microwebframework.Request;
import alexeykf.microwebframework.Response;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    private MicroWebFramework framework;

    @Override
    public void init() {
        String microWebFrameworkClassName = getInitParameter("MicroWebFrameworkClassName");
        try {
            framework = (MicroWebFramework) Class.forName(microWebFrameworkClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Request request = Request.createRequest(req);
        Response response = framework.handle(request);
        resp.setStatus(response.getStatus());

        try (PrintWriter writer = resp.getWriter()) {
            writer.println(response.getBody());
        }
    }
}
