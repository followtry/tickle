/**
 * 
 */
package cn.followtry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import cn.followtry.service.RedisService;

/**
 * @author jingzz
 * @time 2016年6月3日 下午7:01:45
 * @name brief-service/cn.jingzz.brief.service.impl.RedisServiceImpl
 * @since 2016年6月3日 下午7:01:45
 */
@Service
public class RedisServiceImpl implements RedisService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void set(Object key, Object value) {
		getOpsForValue().set(key, value);
	}

	@Override
	public Object get(Object key) {
		Object value = getOpsForValue().get(key);
		return value;
	}
	
	private ValueOperations<Object, Object> getOpsForValue(){
		@SuppressWarnings("unchecked")
		ValueOperations<Object,Object> opsForValue = redisTemplate.opsForValue();
		return opsForValue;
	}

}
