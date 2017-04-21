package cn.followtry.incubate.org.zookeeper;

import java.io.IOException;
import org.apache.zookeeper.ZooKeeper;

/**
 * 测试代码.
 *
 * @author jingzz
 */
public class ZookeeperTest {
  /**
   * main方法.
   *
   * @param args 参数
   * @throws IOException          io异常
   * @throws InterruptedException 中断异常
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    String hosts = "192.168.100.21";
    ZooKeeper zkCli = new ZookeeperClient(hosts).getZooKeeper();
    boolean connected = zkCli.getState().isConnected();
    System.out.println(connected);
  }
}
