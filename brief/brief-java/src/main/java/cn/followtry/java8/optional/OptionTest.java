package cn.followtry.java8.optional;

import java.util.Optional;

/**
 * Created by jingzhongzhi on 2017/9/17.
 */
public class OptionTest {
    /** main. */
    public static void main(String[] args) {
        Optional<Object> optional2 = Optional.ofNullable(null);
        optional2.ifPresent(System.out::println);
    
        Optional<MyOption> option1 = Optional.of(new MyOption());
        option1.ifPresent(System.out::println);
        
    }
}
