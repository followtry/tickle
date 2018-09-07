package cn.followtry.mybatis.plugin;

import lombok.Data;

import java.util.List;

/**
 * 存储日志信息
 * @author jingzhongzhi
 * @date 2017/10/23
 */
@Data
public class LogEntity {
    private List<Object> params;
    
    private String sql;
    
    private String invokeMethod;
    
    private Integer resultSize;
}
