package cn.followtry.mybatis.plugin;

import java.util.Collections;
import java.util.List;

/**
 * Created by jingzhongzhi on 2017/10/21.
 */
public class LogContext {
    static ThreadLocal<LogEntity> context = new ThreadLocal<>();
    
    public static List getParamList() {
        return context.get() == null ? Collections.emptyList() : context.get().getParams();
    }
    
    public static void setParamList(List paramList) {
        if (paramList == null || paramList.size() == 0) {
            return;
        } else if (context.get() == null) {
            context.set(new LogEntity());
        }
        context.get().setParams(paramList);
    }
    
    public static LogEntity getLogEntity(){
        if (context.get() == null) {
            context.set(new LogEntity());
        }
        return context.get();
    }
    
    public static void setLogEntity(LogEntity logEntity){
        context.set(logEntity);
    }
    
}
