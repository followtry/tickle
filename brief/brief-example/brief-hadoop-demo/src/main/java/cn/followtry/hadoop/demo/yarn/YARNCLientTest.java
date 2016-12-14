/**
 * 
 */
package cn.followtry.hadoop.demo.yarn;

import org.apache.hadoop.yarn.client.api.AMRMClient.ContainerRequest;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync.CallbackHandler;
import org.apache.hadoop.yarn.client.api.async.impl.AMRMClientAsyncImpl;

/**
 * @author jingzz
 * @time 2016年12月13日 下午6:31:12
 * @name brief-hadoop-demo/cn.followtry.hadoop.demo.YARNCLientTest
 * @since 2016年12月13日 下午6:31:12
 */
public class YARNCLientTest {
	public static void main(String[] args) {
		
		int intervalMs = 30;
		CallbackHandler callbackHandler = null;
		AMRMClientAsync<ContainerRequest> clientAsync = new AMRMClientAsyncImpl<>(intervalMs, callbackHandler);
	}
}
