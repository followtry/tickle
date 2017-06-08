package cn.followtry.design.pattern.command;

/**
 * Created by followtry on 17/6/7.
 */
public class ChickenWing {
  
  //羊肉串数量
  private static final Integer CHICKEN_WING_SIZE = 25;
  
  //剩余数量
  private static Integer surPlus = CHICKEN_WING_SIZE;
  
  
  public static Integer getSurPlus() {
    return surPlus;
  }
  
  public static void setSurPlus(Integer surPlus) {
    ChickenWing.surPlus = surPlus;
  }
  
  /**
   * 减少存量
   * @param newOrderNum
   * @return
   */
  public static Boolean cutDown(Integer newOrderNum) {
    if (surPlus >= newOrderNum) {
      surPlus -= newOrderNum;
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
