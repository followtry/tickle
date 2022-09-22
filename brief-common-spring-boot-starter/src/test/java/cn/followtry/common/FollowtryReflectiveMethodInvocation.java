package cn.followtry.common;

import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author followtry
 * @since 2022/7/13 11:50 上午
 */
public class FollowtryReflectiveMethodInvocation extends ReflectiveMethodInvocation {

    protected FollowtryReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
        super(proxy, target, method, arguments, targetClass, interceptorsAndDynamicMethodMatchers);
    }

    private MethodProxy methodProxy;

    public FollowtryReflectiveMethodInvocation(Object proxy, @Nullable Object target, Method method,
                                               Object[] arguments, @Nullable Class<?> targetClass,
                                               List<Object> interceptorsAndDynamicMethodMatchers, MethodProxy methodProxy) {

        super(proxy, target, method, arguments, targetClass, interceptorsAndDynamicMethodMatchers);

        // Only use method proxy for public methods not derived from java.lang.Object
        this.methodProxy = (Modifier.isPublic(method.getModifiers()) &&
                method.getDeclaringClass() != Object.class && !AopUtils.isEqualsMethod(method) &&
                !AopUtils.isHashCodeMethod(method) && !AopUtils.isToStringMethod(method) ?
                methodProxy : null);
    }

    /**
     * Gives a marginal performance improvement versus using reflection to
     * invoke the target when invoking public methods.
     */
    @Override
    protected Object invokeJoinpoint() throws Throwable {
        if (this.methodProxy != null) {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
        else {
            return super.invokeJoinpoint();
        }
    }
}
