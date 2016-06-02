/**
 * 
 */
package cn.jingzztech.prac.org.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jingzz
 * @time 2016年2月24日 下午1:22:52
 * @func 
 * @name AbstractZooKeeper
 */ 
public class AbstractZooKeeper implements Watcher {

	private static Logger LOG = LoggerFactory.getLogger(AbstractZooKeeper.class);

	//缓存时间  
	private static final int SESSION_TIME   = 2000;   
	
	protected ZooKeeper zooKeeper;  
	
	protected CountDownLatch countDownLatch=new CountDownLatch(1);  
	
	public ZooKeeper getZooKeeper(){
		return zooKeeper;
	}
	
	@Override
	public void process(WatchedEvent event) {
		  if(event.getState()==KeeperState.SyncConnected){  
	            countDownLatch.countDown();  
	        }  
	}
	  /**
	   * 
	   * @param hosts host+port  使用默认端口（2181）时可以省略端口。e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002" 
	   * @throws IOException
	   * @throws InterruptedException
	   */
     public void connect(String hosts) throws IOException, InterruptedException{     
            zooKeeper = new ZooKeeper(hosts,SESSION_TIME,this);     
            countDownLatch.await();     
      }     

     public void close() throws InterruptedException{     
        zooKeeper.close();     
        LOG.info("已经关闭ZooKeeper");
    }    

}
