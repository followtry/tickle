package cn.followtry.design.pattern.state;

/**
 * Created by followtry on 17/6/15.
 */
public class ForenoonState extends AbstractState {
  @Override
  void writeWorkState(Work work) {
    if (work.getHour() < 11) {
      System.out.println("当前时间为"+work.getHour()+"时，上午时间，工作热情饱满");
    }else{
      work.setCurrentState();
    }
  }
}
