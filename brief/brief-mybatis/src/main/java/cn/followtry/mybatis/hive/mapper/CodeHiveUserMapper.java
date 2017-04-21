package cn.followtry.mybatis.hive.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

/**
 * .
 *
 * @author jingzz
 * @since 2016年10月18日 下午3:01:35
 */
@Mapper
@MapperScan(basePackageClasses = CodeHiveUserMapper.class)
public interface CodeHiveUserMapper {

  @Select("show databases")
  List<String> getDbList();

  /**
   * .
   */
  @Select("show tables")
  List<String> getTablelist();
}
