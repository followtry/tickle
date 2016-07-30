/**
 * 
 */
package cn.jingzztech.prac.org.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

/**
 * @author jingzz
 * @time 2016年2月24日 上午8:54:30
 * @func 
 * @name ZookeeperTest
 */
public class ZookeeperTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		String hosts = "192.168.100.21";
		ZooKeeper zkCli = new ZookeeperClient(hosts).getZooKeeper();
		boolean connected = zkCli.getState().isConnected();
		System.out.println(connected);
	}
}
