package cn.followtry.design.pattern.responsibilitychain;

/**
 * 责任链模式主测试类
 * Created by followtry on 17/6/8.
 */
public class ChainMainTest {
  /** main. */
  public static void main(String[] args) {
    GeneralManager generalManager = new GeneralManager("王五总经理",null);
    Majordemo majordemo = new Majordemo("赵四总监",generalManager);
    CommonManager commonManager = new CommonManager("张三经理",majordemo);
  
    Request request = new Request();
    request.setRequestContent("我是王二麻子，家中有事请假！");
    request.setRequestNum(6);
    request.setRequestType(Request.RequestType.LEAVE);
    
    commonManager.request(request);
  }
}
