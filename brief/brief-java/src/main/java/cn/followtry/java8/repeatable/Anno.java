package cn.followtry.java8.repeatable;

import lombok.Data;

/**
 * 重复注解。
 * 注解可以为任何类型添加注解，包括局部变量、泛型类、父类与接口的实现，连方法的异常也能添加注解。
 * Created by jingzhongzhi on 2017/9/17.
 */
@Data
@AnnoType1(value = "123")
@AnnoType1(value = "")
public class Anno {
    
    private String id;
    
    
    @AnnoTypeArr({@AnnoType1("456"),
            @AnnoType1("123")})
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(@AnnoType1("name") String name) {
        this.name = name;
    }
}
