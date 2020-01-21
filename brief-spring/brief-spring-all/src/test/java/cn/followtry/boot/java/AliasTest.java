package cn.followtry.boot.java;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2020/1/14
 */
public class AliasTest {

    public static Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    /**
     * main.
     */
    public static void main(String[] args) {

        registerAlias("hello", "hello1");
        registerAlias("hello1", "hello2");
        registerAlias("hello2", "hello3");
        registerAlias("hello3", "hello4");
        registerAlias("hello4", "hello5");
        registerAlias("hello5", "hello6");
        registerAlias("hello6", "hello7");
        registerAlias("hello7", "hello8");
        registerAlias("hello8", "hello9");
        registerAlias("hello9", "hello10");
        for (int i = 0; i < 999990; i++) {
            registerAlias("abc", "abc" + i);
        }


        String[] hellos = getAliases("hello");
        System.out.println(JSON.toJSONString(hellos));
    }

    public static void registerAlias(String name, String alias) {
        aliasMap.put(alias, name);
    }

    public static String[] getAliases(String name) {
        List<String> result = new ArrayList<>();
        int i = 0;
        synchronized (aliasMap) {
            i += retrieveAliases(name, result);
        }
        System.out.println("i执行次数:" + i);
        return StringUtils.toStringArray(result);
    }

    private static int retrieveAliases(String name, List<String> result) {
        int i = 0;
        for (Map.Entry<String, String> entry : aliasMap.entrySet()) {
            String alias = entry.getKey();
            String registeredName = entry.getValue();
            i++;
            if (registeredName.equals(name)) {
                result.add(alias);
                i += retrieveAliases(alias, result);
            }
        }
        return i;
    }
}
