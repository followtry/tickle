package cn.followtry.java8.defaultandstatic;

/**
 * Created by jingzhongzhi on 2017/9/17.
 */
public class DefaultTest {
    /** main. */
    public static void main(String[] args) {
        System.out.println("开始");
        DefaultMethodImpl defaultMethod = new DefaultMethodImpl();
        System.out.println(defaultMethod.display());
        System.out.println(defaultMethod.showMe());
        System.out.println("结束");
    }
}
