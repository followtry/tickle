package cn.followtry.comm.token;
/**
 * 
 */

import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 维护全局token信息
 * <br>
 *  brief-comm/cn.followtry.comm.token.TokenManager
 * @author 
 *		jingzz 
 * @since 
 *		2017年1月10日 下午3:16:53
 */
public class TokenManager {

	private static final Logger LOG = LoggerFactory.getLogger(TokenManager.class);

	private static TokenManager imTokenManager;

	private ConcurrentHashMap<String, Token> tokenMap;

	private TokenManager() {
		tokenMap = new ConcurrentHashMap<String, Token>();
	}

	public static synchronized TokenManager getInstance() {
		if (imTokenManager == null) {
			imTokenManager = new TokenManager();
			removeExpireTokenThread();
		}
		return imTokenManager;
	}

	/**
	 * 开启后台移除过期token的线程
	 * 
	 * @author jingzz
	 */
	private static void removeExpireTokenThread() {
		Timer timer = new Timer();

		// 线程启动延时为0
		long delay = 0;
		// 5分钟轮询一次
		long period = 1000 * 60 * 5;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				LOG.info("启动线程检查过期token");
				long cutTime = System.currentTimeMillis();

				long interval = 1000 * 60 * 5;
				TokenManager instance = getInstance();
				Enumeration<String> keySet = instance.keySet();
				while (keySet.hasMoreElements()) {
					String key = keySet.nextElement();
					Token token = instance.get(key);
					long timeInterval = token.getExpiration() - cutTime;
					// token过期，将token从map中移除
					if (token != null && timeInterval < interval) {
						Token oldToken = instance.remove(key);
						LOG.info("移除已经过期的token：key=" + key + ",value=" + oldToken);
					}
				}

			}
		}, delay, period);
	}
	
	/**
	 * 存入或替换token
	 * 
	 * @author jingzz
	 * @param key
	 * @param value
	 * @return 返回值为原来的token，如果不存在返回null
	 */
	public Token put(String key, Token value) {
		Token put = tokenMap.put(key, value);
		return put;
	}

	/**
	 * 通过key获取映射的token信息
	 * 
	 * @author jingzz
	 * @param key
	 * @return 存在则返回token，否则返回null
	 */
	public Token get(String key) {
		Token value = tokenMap.get(key);
		return value;
	}

	public Enumeration<String> keySet() {
		Enumeration<String> keys = tokenMap.keys();
		return keys;
	}

	/**
	 * 移除token
	 * 
	 * @author jingzz
	 * @param key
	 * @return 如果该key存在，返回原来的token。否则返回null
	 */
	public Token remove(String key) {
		Token token = tokenMap.remove(key);
		return token;
	}
}
