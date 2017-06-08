package cn.followtry.zk.example.discovery;

/**
 * Created by followtry on 2017/5/31.
 */
public class InstanceDetails {
  private String        description;
  
  public InstanceDetails()
  {
    this("");
  }
  
  public InstanceDetails(String description)
  {
    this.description = description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getDescription()
  {
    return description;
  }
}
