package cn.jingzztech.prac.mybatis.mapper;

import cn.jingzztech.prac.mybatis.bean.TestTable;

public interface TestTableMapper {
    int deleteByPrimaryKey(Integer testId);

    int insert(TestTable record);

    int insertSelective(TestTable record);

    TestTable selectByPrimaryKey(Integer testId);

    int updateByPrimaryKeySelective(TestTable record);

    int updateByPrimaryKey(TestTable record);
}