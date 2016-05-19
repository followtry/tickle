package cn.jingzztech.prac.org.mybatis.mapper;

import cn.jingzztech.prac.org.mybatis.bean.TestTable;

public interface TestTableMapper {
    int deleteByPrimaryKey(Integer testId);

    int insert(TestTable record);

    int insertSelective(TestTable record);

    TestTable selectByPrimaryKey(Integer testId);

    int updateByPrimaryKeySelective(TestTable record);

    int updateByPrimaryKey(TestTable record);
}