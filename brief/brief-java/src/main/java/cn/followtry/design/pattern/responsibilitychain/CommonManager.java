package cn.followtry.design.pattern.responsibilitychain;

/**
 * 经理
 * Created by followtry on 17/6/8.
 */
public class CommonManager extends AbstractManager {
  
  public CommonManager(String name,AbstractManager superior) {
    super(name,superior);
  }
  

  @Override
  void request(Request request) {
    System.out.println("请求内容：" + request);
    switch (request.getRequestType()) {
      case LEAVE:
        if (request.getRequestNum() < 3) {
          System.out.println(name + "：同意请假" + request.getRequestNum() + "天！");
        } else if (superior != null) {
          System.out.println(name + ":我没有权限，帮你请示下上级！");
          superior.request(request);
        }
        break;
      case SALARY_RAISE:
        if (superior != null) {
          System.out.println(name + ":我没有权限，帮你请示下上级！");
          superior.request(request);
        }
        break;
      default:
      
    }
  }
}
