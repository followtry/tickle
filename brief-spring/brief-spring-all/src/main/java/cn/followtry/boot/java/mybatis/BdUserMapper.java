package cn.followtry.boot.java.mybatis;

import cn.followtry.boot.java.mybatis.BdUserDO;
import java.util.List;

public interface BdUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BdUserDO record);

    BdUserDO selectByPrimaryKey(Long id);

    List<BdUserDO> selectAll();

    int updateByPrimaryKey(BdUserDO record);
}