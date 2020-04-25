

import javax.servlet.*;
import java.io.IOException;
import java.io.Writer;

/**
 * 用于测试 服务器的servlet
 */
public class MyServletTest implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String errorMessage = "HTTP/1.1 200 OK\n\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: \r\n" +
                "\r\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>myH5</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    myServletTest\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        Writer writer=servletResponse.getWriter();
        writer.write(errorMessage);
        writer.flush();
        System.out.println("YTT_____________MyServletTest");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
