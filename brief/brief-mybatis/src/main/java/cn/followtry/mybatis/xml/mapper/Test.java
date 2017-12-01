package cn.followtry.mybatis.xml.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingzhongzhi on 2017/10/21.
 */
public class Test {
    /** main. */
    public static void main(String[] args) {
        String sql = "select * from user\n" + "\t   where id =? and name = ? limit 100";
        List<Object> params = new ArrayList<>();
        params.add("2");
        params.add("jingzhongzhi");
        String[] split = sql.split("\\?");
        StringBuilder sb = new StringBuilder();
        for (int i=0;i < split.length ;i++) {
            if (i < params.size()) {
                sb.append(split[i]).append(params.get(i));
            }else{
                sb.append(split[i]);
            }
        }
        System.out.println(sb.toString());
        
    }
}
