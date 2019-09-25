package cn.followtry.comm.spring.converter;

/**
 * @Description: 类型转换接口
 * @author: haozhongweng
 * @create: 2018/6/29 12:00
 */
@FunctionalInterface
public interface ITypeConverter<T> {
    T convert(String s) throws Exception;
}
