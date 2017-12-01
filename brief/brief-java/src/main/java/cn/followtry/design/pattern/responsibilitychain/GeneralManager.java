package cn.followtry.design.pattern.responsibilitychain;

/**
 * 经理
 * Created by followtry on 17/6/8.
 */
public class GeneralManager extends AbstractManager {
  
  public GeneralManager(String name,AbstractManager superior) {
    super(name,superior);
  }
  

  @Override
  void request(Request request) {
    switch (request.getRequestType()) {
      case LEAVE:
        if (request.getRequestNum() < 10) {
          System.out.println(name+"：同意请假"+request.getRequestNum()+"天！");
        } else if (superior != null) {
          superior.request(request);
        } else if (request.getRequestNum() > 10) {
          System.out.println(name+":时间太长了，最多请10天！");
        }
        break;
      case SALARY_RAISE:
        if (superior != null) {
          System.out.println(name+":我没有权限，帮你请示下上级！");
          superior.request(request);
        }else {
          System.out.println(name+":驳回请求！");
        }
        break;
        default:
        
    }
  }
}
