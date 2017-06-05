package cn.followtry.design.pattern.delegation;

/**
 * 看电视的人
 * Created by followtry on 2017/6/5.
 */
public class WatchingTvPerson extends AbstractPerson {
  
  private String programName;
  
  public WatchingTvPerson(String name,String loc,String programName) {
    super(name,loc);
    this.programName = programName;
    System.out.println("我是" + name + "，职位是"+loc+",在看" + programName + "电视");
  }
  
  @Override
  void startWork(String msg) {
    super.startWork(msg);
    stopWatchingTv();
    System.out.println();
  }
  
  private void stopWatchingTv() {
    System.out.println(getName() + "快关了"+programName+"电视。");
  }
  
}
