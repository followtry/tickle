package cn.followtry.zk;

import com.google.common.base.Charsets;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *   zookeeper的增删改查操作
 * </pre>
 *
 * Created by followtry on 2017/6/2.
 */
public class ZkMainTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkMainTest.class);
  static String parentPath = "/myzk";

  static Random random = new Random();
  
  static String path=parentPath +"/test0000000000";
  /** main. */
  public static void main(String[] args) throws Exception{
    ZkClient zkClient = new ZkClient();
    if (zkClient.checkNotExists(path)) {
      zkClient.create(path);
    }
    
    for (int i=0;i< 10;i++){
  
      zkClient.setData(path,("这是第"+(i+1)+"次更新子节点数据").getBytes());
      byte[] bytes = zkClient.getData(path);
      System.out.println("======="+new String(bytes,Charsets.UTF_8)+"=========");
      List<String> children = zkClient.getChildren(parentPath);
      children.forEach(child -> System.out.println("child:"+child));
  
      TimeUnit.SECONDS.sleep(2);
    }
    List<ACL> aclList = zkClient.getAclList(path);
    aclList.forEach(acl -> System.out.println("acl:"+acl));
    zkClient.deleteData(path);
  }
}
