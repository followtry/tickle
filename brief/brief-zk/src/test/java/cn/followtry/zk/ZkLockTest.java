package cn.followtry.zk;

import cn.followtry.zk.example.lock.ExampleClientThatLocks;
import cn.followtry.zk.example.lock.FakeLimitedResource;
import cn.followtry.zk.utils.ZkProp;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * <pre>
 *   原生zookeeper操作
 * </pre>
 *
 * @author jingzz
 * @since 2016年12月30日 下午1:15:56
 */
public class ZkLockTest {
  
  private static final int QTY = 5;
  
  private static final int REPETITIONS = QTY * 10;
  
  public static final String PATH = "/jingzz/test";
  
  public static final FakeLimitedResource resource = new FakeLimitedResource();
  
  private static ZooKeeper zk;
  
  /**
   * .
   */
  public static void main(String[] args) throws Exception {
  
    zk = new ZooKeeper(ZkProp.connectionString(),ZkProp.sessionTimeoutMs(),
            (event) -> {
      while (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
        System.out.println("节点数据更新");
        try {
          zk.getData(PATH,true,null);
        } catch (KeeperException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        };
      }
    },ZkProp.canBeReadOnly());
  
    //    testLocking();
  
  }
  
  /**
   * 测试zookeeper的排斥锁功能
   * @throws InterruptedException
   */
  private static void testLocking() throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(QTY);
    
    for (int i = 0; i < QTY; i++) {
      final int index = i+1;
      Callable task = () -> {
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString(ZkProp.connectionString())
                .sessionTimeoutMs(ZkProp.sessionTimeoutMs())
                .connectionTimeoutMs(ZkProp.connectionTimeoutMs())
                .retryPolicy(ZkProp.retryPolicy())
                .build();
        try {
          zkClient.start();
    
          ExampleClientThatLocks thatLocks = new ExampleClientThatLocks(zkClient,PATH,resource,"client" + index);
          for (int j = 0; j < REPETITIONS; j++) {
            thatLocks.doWork(10,TimeUnit.SECONDS);
          }
        } finally {
          CloseableUtils.closeQuietly(zkClient);
        }
  
        return null;
      };
      Future submit = service.submit(task);
    }
    service.shutdown();
    service.awaitTermination(10,TimeUnit.SECONDS);
  }
}
