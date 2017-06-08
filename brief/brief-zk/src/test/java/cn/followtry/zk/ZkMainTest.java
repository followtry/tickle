package cn.followtry.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Charsets;

/**
 * Created by followtry on 2017/6/2.
 */
public class ZkMainTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkMainTest.class);
  /** main. */
  public static void main(String[] args) {
    ZkClient zkClient = new ZkClient();
    zkClient.openSession();
    String path ="/myzk/test0000000000";
    ZkNodeCacheTest zkNodeCacheTest = new ZkNodeCacheTest(zkClient,path);
    try {
      byte[] bytes = zkClient.getClient().getData().forPath(path);
      System.out.println(new String(bytes,Charsets.UTF_8));
      zkClient.getClient().setData().forPath("/myzk/test0000000000","125487".getBytes());
    } catch (Exception e) {
      LOGGER.info("获取数据异常",e);
    }
  }
}
