package cn.followtry.zk;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

/**
 * Created by followtry on 2017/6/1.
 */
public class ZkClient {

  private CuratorFramework client;
  
  public ZkClient() {
    client = ZkClientFactory.getZkClient();
  }
  
  public ZkClient openSession(){
    client.start();
    return this;
  }
  
  public String createPath(boolean creatingParentsIfNeeded,CreateMode createMode,List<ACL> aclList,String path,byte[] data) throws Exception {
    CreateBuilder createBuilder = client.create();
    if (creatingParentsIfNeeded) {
      createBuilder.creatingParentsIfNeeded();
    }
    String resPath = createBuilder.withMode(CreateMode.PERSISTENT_SEQUENTIAL)
            .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
            .forPath(path,data);
    return resPath;
  }
  
  public CuratorFramework getClient() {
    return client;
  }
}
