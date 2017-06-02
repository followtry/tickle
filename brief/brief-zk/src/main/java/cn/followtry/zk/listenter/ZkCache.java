package cn.followtry.zk.listenter;

import cn.followtry.zk.ZkClient;
import cn.followtry.zk.ZnodeStat;
import com.google.common.base.Charsets;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by followtry on 2017/6/2.
 */
public class ZkCache {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ZkCache.class);
  
  public static PathChildrenCache pathChildrenCache(final CuratorFramework client,String path,Boolean
          cachedData) {
    PathChildrenCache pathChildrenCache = new PathChildrenCache(client,path,true);
    PathChildrenCacheListener listener = (client2,event) -> {
      switch (event.getType()) {
        case CHILD_ADDED://子节点添加
          pathChildrenCache.rebuild();
          dealTask(event,"创建子节点");
          break;
        case INITIALIZED:
          dealTask(event,"初始化");
          break;
        case CHILD_REMOVED:
          dealTask(event,"删除子节点");
          break;
        case CHILD_UPDATED:
          dealTask(event,"更新子节点");
          break;
        case CONNECTION_LOST:
          dealTask(event,"断开连接");
          break;
        case CONNECTION_SUSPENDED:
          dealTask(event,"挂起连接");
          break;
        case CONNECTION_RECONNECTED:
          dealTask(event,"重连连接");
          break;
        default:
          dealTask(event,"默认事件");
  
      }
    };
    return pathChildrenCache;
  }
  
  private static void dealTask(PathChildrenCacheEvent event,String msg) throws IllegalAccessException, InvocationTargetException {
    byte[] data = event.getData().getData();
    String path1 = event.getData().getPath();
    Stat stat = event.getData().getStat();
    ZnodeStat znodeStat = new ZnodeStat();
    BeanUtils.copyProperties(znodeStat,stat);
    LOGGER.info("信息：{}",msg);
    LOGGER.info("数据：{}",new String(data,Charsets.UTF_8));
    LOGGER.info("stat：{}",znodeStat);
    LOGGER.info("路径：{}",path1);
  }
  
  /** main. */
  public static void main(String[] args) {
    pathChildrenCache.getListenable().addListener(listener);
    try {
      pathChildrenCache.start();
    } catch (Exception e) {
      LOGGER.error("事件监控异常",e);
    }
  }
  
  
}
