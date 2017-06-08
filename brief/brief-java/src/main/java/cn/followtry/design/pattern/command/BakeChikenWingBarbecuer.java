package cn.followtry.design.pattern.command;

/**
 * 烤鸡翅的师傅
 * Created by followtry on 17/6/7.
 */
public class BakeChikenWingBarbecuer implements Technician {

  @Override
  public String executeCommand(Integer newOrderNum) {
    Integer surPlus = ChickenWing.getSurPlus();
    if (surPlus >= newOrderNum) {
      System.out.println("我是烤翅师傅，开始烤翅");
      System.out.println("还剩下"+ChickenWing.getSurPlus()+"个！");
      ChickenWing.cutDown(newOrderNum);
      return "您的"+newOrderNum+"串鸡翅考好了";
    }
    System.out.println("我是烤翅师傅，今天生意火爆，鸡翅只剩下"+surPlus+"个了！");
    ChickenWing.cutDown(surPlus);
    return "您的"+surPlus+"个鸡翅考好了！";
  }

//  @Override
//  public String refuseCommand(Integer newOrderNum) {
  //    System.out.println("我是烤串师傅，没烤串了，不能烤了！");
//    return null;
//  }
}
