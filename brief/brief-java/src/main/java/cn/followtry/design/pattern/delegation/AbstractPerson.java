package cn.followtry.design.pattern.delegation;

/**
 * Created by followtry on 2017/6/5.
 */
public abstract class AbstractPerson extends AbstractWorker {

  private String name;
  
  private String loc;
  
  public AbstractPerson(String name,String loc) {
    this.name = name;
    this.loc = loc;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getLoc() {
    return loc;
  }
  
  public void setLoc(String loc) {
    this.loc = loc;
  }
}
