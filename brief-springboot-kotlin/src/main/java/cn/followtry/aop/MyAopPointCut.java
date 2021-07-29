package cn.followtry.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author followtry
 * @since 2021/7/29 11:51 上午
 */
public class MyAopPointCut extends StaticMethodMatcherPointcut {

    public MyAopPointCut() {
        super();
        setClassFilter(new MyAopClassFilter());
    }

    @Override
    public void setClassFilter(ClassFilter classFilter) {
        super.setClassFilter(classFilter);
    }

    @Override
    public ClassFilter getClassFilter() {
        return super.getClassFilter();
    }

    /**
     * 方法级别判断是否能匹配
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Annotation[] annotations = method.getAnnotations();
        if (annotations == null) {
            return false;
        }
        Optional<Annotation> first = Arrays.stream(annotations).filter(anno -> anno instanceof MyAopLog).findFirst();
        return first.isPresent();
    }

    /**
     * 参考自 TransactionAttributeSourceClassFilter
     *
     * 类级别的匹配过滤器
     */
    private static class MyAopClassFilter implements ClassFilter {
        @Override
        public boolean matches(Class<?> clazz) {
            return AnnotationUtils.isCandidateClass(clazz, MyAopLog.class);
        }
    }

}
