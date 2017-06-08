package cn.followtry.hadoop.demo.util;

import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import org.apache.hadoop.hdfs.server.namenode.ha.proto.HAZKInfoProtos;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过zookeeper选举获取到处于活跃状态的HDFS的NameNode节点
 * Created by followtry on 2017/5/25.
 */
public class HdfsHaElection {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(HdfsHaElection.class);
  
  private static final String HDFS_HA_NODE_PATH = "/hadoop-ha/mycluster/ActiveBreadCrumb";
  private static ZooKeeper zk;
  
  private static Stat stat = new Stat();
  
  private static String hdfs_ha_active_node;
  
  private static String connections = "h2m1:2181,h2s1:2181,h2s2:2181";
  
  static {
    try {
      zk = new ZooKeeper(connections,3000,new Watcher() {
        @Override
        public void process(WatchedEvent event) {
          if (Event.EventType.NodeDataChanged.equals(event.getType())) {
             update();
            LOGGER.info("HA 节点已经更改");
          }
      
        }
      });
    } catch (IOException e) {
      LOGGER.error("zookeeper连接异常",e);
    }
    update();
  }
  
  static void update(){
    byte[] data = new byte[0];
    try {
      data = zk.getData(HDFS_HA_NODE_PATH,true,stat);
    } catch (KeeperException e) {
      LOGGER.error("keeper error",e);
    } catch (InterruptedException e) {
      LOGGER.error("中断 error",e);
    }
    HAZKInfoProtos.ActiveNodeInfo activeNodeInfo = null;
    try {
      activeNodeInfo = HAZKInfoProtos.ActiveNodeInfo.parseFrom(data);
    } catch (InvalidProtocolBufferException e) {
      LOGGER.error("非法协议异常",e);
    }
    hdfs_ha_active_node = activeNodeInfo.getHostname();
  }
  
  public static String getActiveHaNode() {
    return hdfs_ha_active_node;
  }
  
  /** main. */
  public static void main(String[] args) {
    System.out.println(getActiveHaNode());
  }
}
