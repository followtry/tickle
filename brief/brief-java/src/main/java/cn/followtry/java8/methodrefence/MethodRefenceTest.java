package cn.followtry.java8.methodrefence;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

/**
 * 方法引用的方式的使用
 * Created by jingzhongzhi on 2017/9/17.
 */
public class MethodRefenceTest {
    /** main. */
    public static void main(String[] args) {
        MethodRefence refence = new MethodRefence();
        refence.setId("abc");
        refence.setName("Name");
    
        /**
         * 1.构造器引用
         * 2.方法引用
         * 3.静态方法引用
         * 4.实例的方法引用
         */
        Runnable aNew = MethodRefence::new;
        Supplier<String> getStaticName = MethodRefence::getStaticName;
        Function<MethodRefence, String> getName = MethodRefence::getName;
        Supplier<String> getId = refence::getId;
    
        System.out.println();
    }
}
