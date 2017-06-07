package cn.followtry.design.pattern.proxy;

/**
 * Created by followtry on 17/6/7.
 */
public class ProxyMainTest {
  /** main. */
  public static void main(String[] args) {
    RealSubject realSubject = new RealSubject();
    ProxySubject proxy = new ProxySubject(realSubject);
    proxy.request();
  }
}
