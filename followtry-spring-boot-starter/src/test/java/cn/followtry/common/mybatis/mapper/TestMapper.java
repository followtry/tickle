package cn.followtry.common.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author followtry
 * @since 2022/8/8 11:31 上午
 */
public interface TestMapper {

    /**
     * 可设置id=502001
     * @param id
     * @return
     */
    @Select("select * from test where id > #{id} limit 2")
    List<Map<String, Object>> queryBy(@Param("id") Long id);
}
