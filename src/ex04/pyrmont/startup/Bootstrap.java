/* explains Tomcat's default container */
package ex04.pyrmont.startup;

import ex04.pyrmont.core.SimpleContainer;
import org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap {
  public static void main(String[] args) {
    HttpConnector connector = new HttpConnector(); // HttpConnector 组件
    SimpleContainer container = new SimpleContainer();
    connector.setContainer(container);
    try {
      connector.initialize(); // 工程方法创建 ServerSocket对象
      connector.start(); // 开启后台 ServerSocket 的监听线程。初始化一点数量的 processor。

      // make the application wait until we press any key.
      System.in.read();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}