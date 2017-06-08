package cn.followtry.design.pattern.responsibilitychain;

/**
 * Created by followtry on 17/6/8.
 */
public class Request {
  
  private RequestType requestType;
  
  private String requestContent;
  
  private Integer requestNum;
  
  enum RequestType {
    SALARY_RAISE,//加薪
    LEAVE;//请假
  }
  
  public RequestType getRequestType() {
    return requestType;
  }
  
  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }
  
  public String getRequestContent() {
    return requestContent;
  }
  
  public void setRequestContent(String requestContent) {
    this.requestContent = requestContent;
  }
  
  public Integer getRequestNum() {
    return requestNum;
  }
  
  public void setRequestNum(Integer requestNum) {
    this.requestNum = requestNum;
  }
  
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Request{");
    sb.append("requestType=").append(requestType);
    sb.append(", requestContent='").append(requestContent).append('\'');
    sb.append(", requestNum=").append(requestNum);
    sb.append('}');
    return sb.toString();
  }
}
