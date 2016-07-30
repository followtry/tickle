/**
 * 
 */
package cn.jingzz.brief.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jingzz.brief.dao.mapper.MyBatisMapper;
import cn.jingzz.brief.dao.model.MyBatis;
import cn.jingzz.brief.service.MybatisService;

/**
 * @author jingzz
 * @time 2016年6月2日 下午8:29:49
 * @name brief-service/cn.jingzz.brief.service.impl.TestServiceImpl
 * @since 2016年6月2日 下午8:29:49
 */
@Service
public class MybatisServiceImpl implements MybatisService {
	
	@Autowired
	private MyBatisMapper myBatisMapper;

	@Override
	public MyBatis test(String id) {
		System.out.println("TestServiceImpl.Test()");
		MyBatis result = myBatisMapper.selectByPrimaryKey(id);
		return result;
	}

}
