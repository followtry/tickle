package cn.followtry.design.pattern.state;

/**
 * Created by followtry on 17/6/15.
 */
public class NoonState extends AbstractState {
  @Override
  void writeWorkState(Work work) {
    if (work.getHour() < 11) {
      System.out.println("当前时间为"+work.getHour()+"时，中午时间，吃饭了午饭好好休息下");
    }else{
      work.setCurrentState(new NoonState());
      work.writeWorkState();
    }
  }
}
