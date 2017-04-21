package cn.followtry.zk;

import java.io.IOException;
import org.apache.zookeeper.KeeperException;

/**
 * brief-zk/cn.followtry.zk.ZKClient
 *
 * @author jingzz
 * @since 2016年12月30日 下午1:15:56
 */
public class ZkClient {

  private static final String connectString = "h2m1:2181,h2s1:2181,h2s2:2181";

  private static int sessionTimeout = 3000;

  static Integer mutex;

  /**
   * .
   */
  public ZkClient() throws IOException {
    System.out.println("开始 zk");
    mutex = -1;
    System.out.println("完成zk");
  }

  /**
   * .
   */
  public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

    //    org.apache.zookeeper.ZooKeeper zk = new org.apache.zookeeper.ZooKeeper(connectString,
    //            sessionTimeout,new org.apache.zookeeper.Watcher() {
    //
    //      @Override
    //      public void process(org.apache.zookeeper.WatchedEvent event) {
    //        while (true) {
    //          System.out.println(event.getPath());
    //          System.out.println(event.getState().name());
    //          System.out.println(event.getType().name());
    //        }
    //      }
    //    });
    //
    //    String createPath = zk.create("/test3","myzookeeper".getBytes(),Ids.OPEN_ACL_UNSAFE,org
    //            .apache.zookeeper.CreateMode.PERSISTENT);
    //    System.out.println("createPath:" + createPath);
    //
    //    java.util.List<String> children = zk.getChildren("/test",true);
    //    System.out.println("children=" + children);

    boolean assignableFrom = ZkClient.class.isAssignableFrom(Object.class);
    System.out.println(assignableFrom);
  }
}
