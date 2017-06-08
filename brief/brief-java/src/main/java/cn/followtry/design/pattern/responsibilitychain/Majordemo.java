package cn.followtry.design.pattern.responsibilitychain;

/**
 * 总监
 * Created by followtry on 17/6/8.
 */
public class Majordemo extends Manager{
  
  public Majordemo(String name,Manager superior) {
    super(name,superior);
  }
  
  @Override
  void request(Request request) {
    switch (request.getRequestType()) {
      case LEAVE:
        if (request.getRequestNum() < 7) {
          System.out.println(name+"：同意请假"+request.getRequestNum()+"天！");
        } else if (superior != null) {
          System.out.println(name+":我没有权限，帮你请示下上级！");
          superior.request(request);
        }
        break;
      case SALARY_RAISE:
        if (request.getRequestNum() < 500) {
          System.out.println(name+"：同意调薪"+request.getRequestNum()+"元！");
        }
        if (superior != null) {
          System.out.println(name+":我没有权限，帮你请示下上级！");
          superior.request(request);
        }
        break;
        default:
        
    }
  }
}
