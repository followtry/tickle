package cn.followtry.design.pattern.responsibilitychain;

/**
 * Created by followtry on 17/6/8.
 */
public class Request {
  
  private RequestType requestType;
  
  private String requestContent;
  
  private Integer requestNum;
  
  enum RequestType {
    /**加薪*/
    SALARY_RAISE,
      /**请假*/
    LEAVE;
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
