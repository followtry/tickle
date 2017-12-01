package cn.followtry.java.rpc;

/**
 * Created by followtry on 2017/6/8 0008.
 */
public class EchoServiceImpl implements EchoService {

  @Override
  public String echo(String name) {
    return name+"Rpc is OK!";
  }
}
