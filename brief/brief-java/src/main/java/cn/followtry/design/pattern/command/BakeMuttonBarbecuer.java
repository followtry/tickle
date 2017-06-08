package cn.followtry.design.pattern.command;

/**
 * Created by followtry on 17/6/7.
 */
public class BakeMuttonBarbecuer implements Technician {

  @Override
  public String executeCommand(Integer newOrderNum) {
    Integer surPlus = Mutton.getSurPlus();
    if (surPlus >= newOrderNum) {
      System.out.println("我是烤串师傅，开始烤串");
      System.out.println("还剩下"+Mutton.getSurPlus()+"串！");
      Mutton.cutDown(newOrderNum);
      return "您的"+newOrderNum+"串羊肉串考好了";
    }
    System.out.println("我是烤串师傅，今天生意火爆，羊肉串只剩下"+surPlus+"串了！");
    Mutton.cutDown(surPlus);
    return "您的"+surPlus+"串羊肉串考好了！";
  }

//  @Override
//  public String refuseCommand(Integer newOrderNum) {
  //    System.out.println("我是烤串师傅，没烤串了，不能烤了！");
//    return null;
//  }
}
