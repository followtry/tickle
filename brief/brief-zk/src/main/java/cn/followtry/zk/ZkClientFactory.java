package cn.followtry.zk;

import cn.followtry.zk.utils.ZkProp;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.zookeeper.ZooKeeper;

/**
 * brief-zk/cn.followtry.zk.ZKClient
 *
 * @author jingzz
 * @since 2016年12月30日 下午1:15:56
 */
public class ZkClientFactory {
  
  private static ZooKeeper zk;
  
  private static CuratorFramework client;
  
  /**
   * .
   */
  public static void main(String[] args) throws Exception {
    
    zk = new ZooKeeper(ZkProp.connectionString(),ZkProp.sessionTimeoutMs(),(event) -> {
      while (true) {
  
        switch (event.getType()) {
          case NodeDeleted:
            System.out.println("NodeDeleted");
            break;
          case NodeDataChanged:
            System.out.println("NodeDataChanged");
            break;
          case NodeCreated:
            System.out.println("NodeCreated");
            break;
          case NodeChildrenChanged:
            System.out.println("NodeChildrenChanged");
            break;
          case None:
            System.out.println("None");
            break;
          default:
            System.out.println("default");
    
        }
      }
    });
  }

  public static CuratorFramework getZkClient() {
    //从配置中读取对客户端的设置
    client = CuratorFrameworkFactory.builder()
              .canBeReadOnly(ZkProp.canBeReadOnly())
              .connectionTimeoutMs(ZkProp.connectionTimeoutMs())
              .sessionTimeoutMs(ZkProp.sessionTimeoutMs())
              .retryPolicy(ZkProp.retryPolicy())
              .connectString(ZkProp.connectionString())
              .build();
    return client;
  }
}
