package cn.followtry.design.pattern.delegation;

import java.util.Arrays;
import java.util.List;

/**
 * 事件委托模式的主测试类
 * Created by followtry on 2017/6/5.
 */
public class DelegationMainTest {
  /** main. */
  public static void main(String[] args) {
  
    String methodName = "startWork";
  
    //老板来了
    String msg="老板来了";
    
    //定义各色各样的人（员工）委托者
    PlayingGamePerson zhangsan = new PlayingGamePerson("张三","用友集团员工","王者荣耀");
    PlayingGamePerson lisi = new PlayingGamePerson("李四","阿里集团员工","王者荣耀");
    PlayingGamePerson wangwu = new PlayingGamePerson("王五","滴滴HR","开心消消乐");
    PlayingGamePerson maliu = new PlayingGamePerson("马六","支付宝运营","穿越火线");
  
    WatchingTvPerson wangfang = new WatchingTvPerson("王芳","OFO会计","三生三世十里桃花");
    WatchingTvPerson zhuyin = new WatchingTvPerson("朱茵","摩拜女程序员","欢乐颂2");
    WatchingTvPerson wengmeiling = new WatchingTvPerson("翁美玲","丐帮帮主","天龙八部");
    WatchingTvPerson gaoyuanyuan = new WatchingTvPerson("高圆圆","峨眉派掌门","倚天屠龙记");
  
    List<AbstractPerson> allPerson = Arrays.asList(zhangsan,lisi,wangwu,maliu,wangfang,
            zhuyin,wengmeiling,gaoyuanyuan);
    System.out.println("=========================================");
  
    
    // 信誉良好的哨兵（前台妹子）观察者
    GoodWatcher receptionist = new GoodWatcher("赵敏");
    
    //委托事件（将委托者和观察者关联起来，完全解耦）,将提醒的事情委托给前台妹子
    allPerson.forEach(person -> receptionist.addListenter(person.getName(),person,methodName,msg));
  
    System.out.println("=========================================");
    
    //前台妹子发现老板来了，赶紧通知其他员工
    receptionist.sendNotify();
  
    System.out.println("结束");
    
  }
}
