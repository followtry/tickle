package cn.followtry.design.pattern.proxy;

/**
 * 真实的业务类
 * Created by followtry on 17/6/7.
 */
public class RealSubject implements StaticSubject{

  @Override
  public void request() {
    System.out.println("this is real subject!");
  }
}
