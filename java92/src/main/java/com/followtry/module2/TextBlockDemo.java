package com.followtry.module2;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/12
 */
public class TextBlockDemo {
    public static void main(String[] args) {
        var sql = """
        select user_id,user_name,age
            from user_info
        where user_id = 1234
            and type = 1
        order by id desc
        limit 10
        """;

        System.out.println(sql);
    }
}
