package cn.followtry.java8.methodrefence;

import lombok.Data;

/**
 * Created by jingzhongzhi on 2017/9/17.
 */
@Data
public class MethodRefence {
    private String id;
    
    private static String staticName;
    
    private String name;
    
    
    public static String getStaticName() {
        return staticName;
    }
    
    public static void setStaticName(String staticName) {
        MethodRefence.staticName = staticName;
    }
}
