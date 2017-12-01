package cn.followtry.java8.defaultandstatic;

/**
 * 接口中的默认方法和静态方法都可以不用实现。而且默认方法可以被实现类实现而静态方法不可以。
 *
 * @author jingzhongzhi
 * @date 2017/9/17
 */
public class DefaultMethodImpl implements DefaultMethodFeature{
    
    @Override
    public String display() {
        showMe();
        return "I'm DefaultMethodImpl";
    }
    
//    @Override
//    public String showMe() {
//        return DefaultMethodImpl.class.getCanonicalName();
//    }
    
}
