/**
 * 
 */
package cn.jingzztech.prac.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * @author jingzz
 * @time 2016年2月24日 下午1:31:27
 * @func 
 * @name ZookeeperClient
 */
public class ZookeeperClient extends AbstractZooKeeper {
	

	public ZookeeperClient() {
	}
	
	public ZookeeperClient(String hosts) throws IOException, InterruptedException {
		connect(hosts);
	}
	
	/**
	 * 创建持久态的znode,比支持多层创建.比如在创建/parent/child的情况下,无/parent.无法通过 
	 * @param path
	 * @param data
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String create(String path,byte[] data)throws KeeperException, InterruptedException{  
        /** 
         * 此处采用的是CreateMode是PERSISTENT  表示The znode will not be automatically deleted upon client's disconnect. 
         * EPHEMERAL 表示The znode will be deleted upon the client's disconnect. 
         */   
        return this.zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);  
    }  
}
