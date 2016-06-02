/**
 * 
 */
package cn.jingzztech.prac.org.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author jingzz
 * @time 2016年2月23日 下午3:01:37
 * @func 
 * @name RedisService
 */
//@Service
public class RedisService {
	/*@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	public void setData(String key,String value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	public Object getData(String key){
		return redisTemplate.opsForValue().get(key);
	}*/
}
