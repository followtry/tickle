package com.followtry.module2;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/12
 */
public class TextBlockDemo {
    public static void main(String[] args) {
        int age = 20;
        var sql = """
        select user_id,user_name,age
            from user_info
        where user_id = 1234
            and type = 1
            and age = 嘻嘻哈哈
        order by id desc
        limit 10
        """;

        String sql2 = "select * from a \n where naem ='hahah' \n limit 10";

        System.out.println(sql);
        System.out.println(sql2);
    }
}
