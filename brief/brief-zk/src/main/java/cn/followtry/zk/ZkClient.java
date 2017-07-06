package cn.followtry.zk;

import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

/**
 * 封装CuratorFramework，使其更易用。
 * <pre>
 *   如果有使用CuratorFramework的特殊需求，可以直接通过getClient方法获得。
 * </pre>
 * Created by followtry on 2017/6/1.
 */
public class ZkClient {

  private CuratorFramework client;
  
  public ZkClient() {
    client = ZkClientFactory.getZkClient();
    client.start();
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
  
  public String create(String path) throws Exception {
    String forPath = client.create().creatingParentsIfNeeded().forPath(path,null);
    return forPath;
  }
  
  public String create(String path,byte[] data) throws Exception {
    String forPath = client.create().creatingParentsIfNeeded().forPath(path,data);
    return forPath;
  }
  
  public Boolean checkExists(String path) throws Exception {
    Stat stat = client.checkExists().forPath(path);
    return stat == null ? Boolean.FALSE : Boolean.TRUE;
  }
  
  public Boolean checkNotExists(String path) throws Exception {
    return !checkExists(path);
  }
  
  public byte[] getData(String path) throws Exception {
    return client.getData().forPath(path);
  }
  
  public Stat setData(String path,byte[] data) throws Exception {
    Stat stat = client.setData().forPath(path,data);
    return stat;
  }
  
  public void deleteData(String path) throws Exception {
    client.delete().deletingChildrenIfNeeded().forPath(path);
  }
  
  public List<String> getChildren(String parentPath) throws Exception {
    List<String> childrenNodes = client.getChildren().forPath(parentPath);
    return CollectionUtils.isEmpty(childrenNodes) ? Collections.emptyList() : childrenNodes;
  }
  
  public List<ACL> getAclList(String path) throws Exception {
   return client.getACL().forPath(path);
  }
}
