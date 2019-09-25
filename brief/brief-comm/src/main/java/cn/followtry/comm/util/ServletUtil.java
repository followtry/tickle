package cn.followtry.comm.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lishaofeng
 * @version 1.0 Created at: 2018-03-24 13:14
 */
public class ServletUtil {

    public static Map<String, String> getParamMap(Map<String, String[]> params) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String[] value = entry.getValue();
            if (value != null && value.length > 0) {
                result.put(entry.getKey(), value[value.length - 1]);
            }
        }
        return result;
    }

}
