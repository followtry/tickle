package cn.followtry.zk;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by followtry on 2017/6/1.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZkClientTest {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientTest.class);
  
  private String path = "/myzk/test";
  
  private ZkClient zkClient;
  
  private CuratorFramework client;
  
  @Before
  public void setup() {
    zkClient = new ZkClient();
    zkClient.openSession();
    client = zkClient.getClient();
  }
  
  /**
   * 创建指定的路径，没有指定值则设置默认值为当前机器的ip地址
   */
  @Test
  public void test010createPath() {
    try {
      String res = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
              .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(path);
      LOGGER.info("创建路径【{}】成功",path);
      Assert.assertNotNull(res);
    } catch (Exception e) {
      LOGGER.error("创建路径【{}】异常，{}",path,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  /**
   * 获取数据
   */
  @Test
  public void test011getData() {
    try {
      String res = getData();
      LOGGER.info("更新前，获取到路径【{}】的数据为【{}】",path,res);
      Assert.assertNotNull(res);
    } catch (Exception e) {
      LOGGER.error("更新前，获取【{}】的数据异常，{}",path,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  private String getData() throws Exception {
    byte[] bytes = client.getData().forPath(path);
    return new String(bytes,"UTF-8");
  }
  
  @Test
  public void test020UpdateData() {
    String data = "更新测试数据：1234567890";
    try {
      Stat res = client.setData().forPath(path,data.getBytes());
      ZnodeStat znodeStat = new ZnodeStat();
//      BeanUtils.copyProperties(znodeStat,res);
  
      LOGGER.info("更新Znode节点【{}】的数据成功",znodeStat.toString());
      Assert.assertNotNull(res);
    } catch (Exception e) {
      LOGGER.error("更新【{}】的数据为【{}】异常",path,data,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  /**
   * 获取数据
   */
  @Test
  public void test021getData() {
    try {
      String res = getData();
      LOGGER.info("更新后，获取到路径【{}】的数据为【{}】",path,res);
      Assert.assertNotNull(res);
    } catch (Exception e) {
      LOGGER.error("更新后，获取【{}】的数据异常，{}",path,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  /**
   * 获取指定节点的子节点
   */
  @Test
  public void test022getChildren() {
    String path1 = "/";
    try {
      List<String> pathList = client.getChildren().forPath(path1);
      pathList.forEach(p -> LOGGER.info("path={}",p));
    } catch (Exception e) {
      LOGGER.error("获取路径【{}】下的子节点异常",path1,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  /**
   * 删除指定节点
   */
  @Test
  public void test090deletePath() {
    try {
      client.delete().deletingChildrenIfNeeded().forPath(path);
      LOGGER.info("删除路径【{}】成功",path);
    } catch (Exception e) {
      LOGGER.error("删除路径【{}】异常，{}",path,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  /**
   * 检查节点是否存在
   */
  @Test
  public void test090CheckPath() {
    try {
      Stat stat = client.checkExists().forPath(path);
      LOGGER.info("删除路径【{}】成功",path);
    } catch (Exception e) {
      LOGGER.error("删除路径【{}】异常，{}",path,e);
      Assert.assertTrue(Boolean.FALSE);
    }
  }
  
  @After
  public void clear() {
    client.close();
  }
  
  
}