package cn.followtry.java8.defaultandstatic;


/**
 * 接口的默认方法和静态方法。可以认为是C++的抽象类的概念
 * Created by followtry on 2017/3/27 0027.
 */
public interface DefaultMethodFeature {

    String display();

    default String showMe(){
        return getMyName();
    }
    
    static String getMyName(){
        return DefaultMethodFeature.class.getCanonicalName();
    }
}
