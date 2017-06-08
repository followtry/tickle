package cn.followtry.design.pattern.command;

/**
 * 我是要吃烧烤的顾客
 * Created by followtry on 17/6/7.
 */
public class CommandMainTest {
  /** main. */
  public static void main(String[] args) {
    Waiter waiter = new Waiter();
    
    
    //桌1的客人
    String s = waiter.acceptOrder(BarBecueKind.Mutton,10);
    String s1 = waiter.acceptOrder(BarBecueKind.ChickenWing,10);
    System.out.println(s+";"+s1+"\n"+waiter.notifyCookHouse());
    System.out.println();
    
    //桌2的客人
    String s2 = waiter.acceptOrder(BarBecueKind.Mutton,11);
    String s3 = waiter.acceptOrder(BarBecueKind.ChickenWing,6);
    System.out.println(s2+";"+s3+"\n"+waiter.notifyCookHouse());
    System.out.println();
  
    //桌3的客人
    String s4 = waiter.acceptOrder(BarBecueKind.Mutton,2);
    String s5 = waiter.acceptOrder(BarBecueKind.ChickenWing,6);
    System.out.println(s4+";"+s5+"\n"+waiter.notifyCookHouse());
    System.out.println();
  
    //桌4的客人
    s4 = waiter.acceptOrder(BarBecueKind.Mutton,2);
    s5 = waiter.acceptOrder(BarBecueKind.ChickenWing,6);
    System.out.println(s4+";"+s5+"\n"+waiter.notifyCookHouse());
  
  
    System.out.println("结束");
  }
}
