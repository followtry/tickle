package cn.followtry.zk.listenter;

import cn.followtry.zk.ZkClient;
import cn.followtry.zk.ZnodeStat;
import com.google.common.base.Charsets;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *  监控某个目录下子目录的变化.
 *  通过while循环，使得监听器一直存在，监听到变化后就直接根据不同的类型进行不同的处理。
 * </pre>
 * Created by followtry on 2017/6/29.
 */
public class ChildHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChildHandler.class);

    PathChildrenCache pathChildrenCache;

    public ChildHandler(ZkClient client, String path) {

        pathChildrenCache = new PathChildrenCache(client.getClient(), path, Boolean.TRUE);
        pathChildrenCache.getListenable().addListener((curClient, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED://子节点添加
                    pathChildrenCache.rebuild();
                    dealTask(event, "创建子节点");
                    break;
                case INITIALIZED:
                    dealTask(event, "初始化");
                    break;
                case CHILD_REMOVED:
                    dealTask(event, "删除子节点");
                    break;
                case CHILD_UPDATED:
                    dealTask(event, "更新子节点");
                    break;
                case CONNECTION_LOST:
                    dealTask(event, "断开连接");
                    break;
                case CONNECTION_SUSPENDED:
                    dealTask(event, "挂起连接");
                    break;
                case CONNECTION_RECONNECTED:
                    dealTask(event, "重连连接");
                    break;
                default:
                    dealTask(event, "默认事件");
            }
        });
    }

    public PathChildrenCache getPathChildrenCache() {
        return this.pathChildrenCache;
    }

    private void dealTask(PathChildrenCacheEvent event, String msg) throws IllegalAccessException, InvocationTargetException {
        ChildData data1 = event.getData();
        LOGGER.info("信息：{}", msg);
        if (data1 == null) {
            LOGGER.error("监控路径下数据为null");
            return;
        }
        byte[] data = data1.getData();
        String path1 = event.getData().getPath();
        Stat stat = event.getData().getStat();
        ZnodeStat znodeStat = new ZnodeStat();
        BeanUtils.copyProperties(znodeStat, stat);
        LOGGER.info("数据：{}", new String(data == null ? "".getBytes() : data, Charsets.UTF_8));
        LOGGER.info("stat：{}", znodeStat);
        LOGGER.info("路径：{}", path1);
        System.out.println();
    }


}
