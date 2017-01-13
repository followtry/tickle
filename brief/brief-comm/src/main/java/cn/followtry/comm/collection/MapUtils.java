package cn.followtry.comm.collection;

import java.util.Map;

/**
 * brief-comm/cn.followtry.comm.collection.MapUtil
 * 
 * @author jingzz
 * @since 2017年1月10日 下午3:17:51
 */
public class MapUtils {
	
	private MapUtils() {}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		return (map == null || map.isEmpty());
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

}
