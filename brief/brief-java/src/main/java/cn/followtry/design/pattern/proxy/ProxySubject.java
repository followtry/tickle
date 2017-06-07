package cn.followtry.design.pattern.proxy;

/**
 * RealSubject的代理类，完成与RealSubject相同的功能
 * Created by followtry on 17/6/7.
 */
public class ProxySubject implements StaticSubject {
  
  private RealSubject realSubject;
  
  public ProxySubject(RealSubject realSubject) {
    this.realSubject = realSubject;
  }
  
  @Override
  public void request() {
    if (realSubject != null) {
      System.out.println("This is proxy subject,Then I will proxy RealSubject");
      realSubject.request();
    }
  }
}
