package cn.followtry.design.pattern.command;

/**
 * Created by followtry on 17/6/7.
 */
public enum  BarBecueKind {
  //羊肉串
  Mutton("羊肉串",1),
  //鸡翅
  ChickenWing("鸡翅",2);
  
  private String name;
  private Integer id;
  BarBecueKind(String name,Integer id) {
    this.name = name;
    this.id = id;
    
  }
  
  public String getName() {
    return this.name;
  }
}
