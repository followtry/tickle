package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/20
 */
public interface TestA {

    Object getB();

    default Object getA(){
        Object b = getB();
        return String.valueOf(b) + 10;
    }
}
