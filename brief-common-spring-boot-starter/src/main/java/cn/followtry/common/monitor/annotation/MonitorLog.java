package cn.followtry.common.monitor.annotation;

import cn.followtry.common.monitor.log.LoggerMonitor;

import java.lang.annotation.*;

/**
 * 监控日志的打点，可同时在Type和Method上注解，Method上注解值会覆盖掉Type上注解值
 *
 * @author followtry
 * @since 2021/10/10 9:31 下午
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MonitorLog {

    /**
     * 默认使用 {@link MonitorType}
     * @return 监控类型
     */
    String typeName();

    /**
     * 日志的<logger name='MONITOR_LOG'></logger> 标签的名称。
     * 使用该logger前需要先在日志配置里定义对应的logger
     *
     * @return 配置的日志名称
     */
    String logName() default "MONITOR_LOG";

    /**
     * 请求表达式,spEL格式,以#开头
     * 可以从请求参数中获取指定的值，不要轻易变更顺序，表达式的顺序决定了写入日志内的顺序
     * 只限于作用于method上，请勿在Type上使用，避免查找不到导致请求异常
     * @return
     */
    String[] reqExpress() default {};

    /**
     * 默认的结果Y或N的表达式，使用SpEl表达式
     * 返回结果对象不用明确表示，只需要从其属性值开始描述
     * <pre>
     *     User u = new User()
     *     u.name = "zhangsan"
     *     Address addr = new Address()
     *     addr.city = "beijing"
     *     u.addr = addr
     *
     *     对于以上的代码。取城市地址使用"addr.city",取名称使用"name"
     *
     * </pre>
     *
     * @return 返回结果是否正确的自定义表达式
     */
    String resExpress() default "";

    /**
     * 简要的方法声明，替换真实的方法全限定名。可以是中文的方法描述等方便阅读和汇总。
     * 只在方法上生效
     * @return
     */
    String methodSummary() default "";

    /**
     * 结果提示信息
     *
     * @return 判断结果的信息的EL表达式，错误时有用
     */
    String resMsg() default "";

    /**
     * 是否需要打印请求日志
     *
     * @return true为打印request的详情，默认为false
     */
    boolean needRequest() default false;

    /**
     * 是否需要打印响应日志
     *
     * @return true为打印response的详情，默认为false
     */
    boolean needResponse() default false;

    /**
     * 自定义的日志处理器，值为注册进SpringIOC容器内的beanName，实现类需要实现{@link LoggerMonitor}接口
     *
     * @return 自定义指定的日志处理器的beanName，默认的日志处理器为DefaultLoggerMonitor
     */
    String logProcessor() default "";
}
