/**
 * 
 */
package cn.jingzztech.prac.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis java的API操纵方法
 * @author jingzz
 * @time 2016年2月23日 下午1:42:28
 * @func 
 * @name RedisClient
 */
public class RedisClient {
	public static void main(String[] args) {
		int port = 6379;
		JedisPool jedisPool = new JedisPool("h2m1", port);
		Jedis client = jedisPool.getResource();
		String str = client.set("name", "jingzhongzhi");
		System.out.println(str);
		String value = client.get("name");
		System.out.println("查询出的值为:"+value);
		
		String clientList = client.clientList();
		System.out.println(clientList);
		client.close();
		jedisPool.close();
	}
}
