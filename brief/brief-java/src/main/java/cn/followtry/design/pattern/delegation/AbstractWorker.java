package cn.followtry.design.pattern.delegation;

/**
 * 具体工作者皆可，定义通用的方法
 * Created by followtry on 2017/6/5.
 */
public abstract class AbstractWorker {

  void startWork(String msg){
    System.out.println(msg);
    System.out.println("快开始工作");
  }

}
