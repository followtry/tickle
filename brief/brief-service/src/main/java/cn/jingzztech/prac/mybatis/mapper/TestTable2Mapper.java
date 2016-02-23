package cn.jingzztech.prac.mybatis.mapper;

import cn.jingzztech.prac.mybatis.bean.TestTable2;
import cn.jingzztech.prac.mybatis.bean.TestTable2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestTable2Mapper {
    int countByExample(TestTable2Example example);

    int deleteByExample(TestTable2Example example);

    int deleteByPrimaryKey(Integer testId);

    int insert(TestTable2 record);

    int insertSelective(TestTable2 record);

    List<TestTable2> selectByExample(TestTable2Example example);

    TestTable2 selectByPrimaryKey(Integer testId);

    int updateByExampleSelective(@Param("record") TestTable2 record, @Param("example") TestTable2Example example);

    int updateByExample(@Param("record") TestTable2 record, @Param("example") TestTable2Example example);

    int updateByPrimaryKeySelective(TestTable2 record);

    int updateByPrimaryKey(TestTable2 record);
}