package cn.followtry.incubate.java8;


/**
 * 接口的默认方法（扩展方法）
 * Created by followtry on 2017/3/27 0027.
 */
public interface DefaultMethodFeature {

    String display();

    default String showMe(){
        return DefaultMethodFeature.class.getCanonicalName();
    }
}
