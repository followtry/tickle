package cn.followtry.zk;

import com.google.common.base.Charsets;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by followtry on 2017/6/2.
 */
public class ZkNodeCacheTest {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkNodeCacheTest.class);
  
  private PathChildrenCache pathChildrenCache;
  
  private String path;
  
  public ZkNodeCacheTest(ZkClient zkClient,String path) {
    this.path = path;
   
  };
  
 
}
