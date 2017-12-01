package cn.followtry.java8.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jingzhongzhi on 2017/9/17.
 */
public class DateTimeTest {
    /** main. */
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(Instant.now());
        System.out.println("结束");
    }
}
