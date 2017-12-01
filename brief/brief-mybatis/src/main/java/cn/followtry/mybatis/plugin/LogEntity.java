package cn.followtry.mybatis.plugin;

import java.util.List;
import lombok.Data;

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
}
