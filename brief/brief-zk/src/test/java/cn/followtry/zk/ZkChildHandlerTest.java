package cn.followtry.zk;

import cn.followtry.zk.listenter.ChildHandler;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zookeeper的监控节点的子节点的变化监控
 * Created by followtry on 2017/6/29.
 */
public class ZkChildHandlerTest {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkChildHandlerTest.class);
  /** main. */
  public static void main(String[] args) throws Exception {
    ChildHandler myzk = new ChildHandler(new ZkClient(),"/myzk");
    myzk.getPathChildrenCache().start();
    while (true) {
      
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        LOGGER.error(e.getMessage(),e);
      }
      
    }
  }
}
