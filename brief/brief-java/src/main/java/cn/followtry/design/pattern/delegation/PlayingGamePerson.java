package cn.followtry.design.pattern.delegation;

/**
 * 玩游戏的人
 * Created by followtry on 2017/6/5.
 */
public class PlayingGamePerson extends AbstractPerson {
  
  private String gameName;

  public PlayingGamePerson(String name,String loc,String gameName) {
    super(name,loc);
    this.gameName = gameName;
    System.out.println("我是" + name + "，职位是"+loc+"，在玩" + gameName + "游戏");
  }
  
  @Override
  void startWork(String msg) {
    super.startWork(msg);
    stopWatchingTv();
    System.out.println();
  }
  
  private void stopWatchingTv() {
    System.out.println(getName()+"快关了"+gameName+"游戏。");
  }
  
}
