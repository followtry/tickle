package cn.jingzz.brief.dao.mapper;

import cn.jingzz.brief.dao.model.MyBatis;

public interface MyBatisMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyBatis record);

    int insertSelective(MyBatis record);

    MyBatis selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyBatis record);

    int updateByPrimaryKey(MyBatis record);
}