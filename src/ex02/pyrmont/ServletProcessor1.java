package ex02.pyrmont;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.io.File;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * servlet 处理
 */
public class ServletProcessor1 {

  public void process(Request request, Response response) {

    String uri = request.getUri();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    URLClassLoader loader = null;

    try {
      // create a URLClassLoader
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      File classPath = new File(Constants.WEB_ROOT);
      // the forming of repository is taken from the createClassLoader method in
      // org.apache.catalina.startup.ClassLoaderFactory
      String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;// servlet 路径
      // the code for forming the URL is taken from the addRepository method in
      // org.apache.catalina.loader.StandardClassLoader class.
      urls[0] = new URL(null, repository, streamHandler);
      loader = new URLClassLoader(urls); // 创建使用URL 类加载器
    }
    catch (IOException e) {
      System.out.println(e.toString() );
    }
    Class myClass = null;
    try {
      myClass = loader.loadClass(servletName);  // 加载对应的servlet
    }
    catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }

    Servlet servlet = null;

    try {
      servlet = (Servlet) myClass.newInstance();

      servlet.service((ServletRequest) request, (ServletResponse) response); // 调用servlet的 service 方法
        //region  用于测试 request 的不安全性
        ServletRequest request1=(ServletRequest) request;

        Request request2=(Request) request1; // 向下转型
        request2.getUri(); // 可以调用一些不应该调用的方法
        System.out.println("YTT________"+this.getClass().getName()+"________"+request2.getUri());
           /*
                1.使用修饰符保护
                 访问权限   类   包  子类  其他包

                public     ∨   ∨   ∨     ∨

                protect    ∨   ∨   ∨     ×

                default    ∨   ∨   ×     ×

                    private    ∨   ×   ×     ×
                2.包装类保护

                    */
        //endregion
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
    catch (Throwable e) {
      System.out.println(e.toString());
    }

  }
}