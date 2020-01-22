package cn.followtry.boot.java.mybatis;

import java.util.List;

public interface BdUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BdUserDO record);

    BdUserDO selectByPrimaryKey(Long id);

    List<BdUserDO> selectAll();

    int updateByPrimaryKey(BdUserDO record);
}