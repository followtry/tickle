/**
 * 
 */
package cn.jingzz.brief.service;

/**
 * @author jingzz
 * @time 2016年6月3日 下午7:01:04
 * @name brief-service/cn.jingzz.brief.service.RedisService
 * @since 2016年6月3日 下午7:01:04
 */
public interface RedisService {
	
	void set(Object key,Object value);
	
	Object get(Object key);
}
