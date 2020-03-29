package cn.followtry.boot.java;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author jingzhongzhi
 * @since 2020/2/23
 */
public class KmpDemo {
    
    /**
     * main.
     */
    public static void main(String[] args) {
    
        RateLimiter rateLimiter = RateLimiter.create(10,5, TimeUnit.MINUTES);
        rateLimiter.tryAcquire();
    
        String a = "abaabaabbabaaabaabbabaabcde";
        String s = "abaabbabaabcde";
        int[] nextArr = getNextArr(s);
        System.out.println(JSON.toJSONString(nextArr));
        
        int startIndex = getStartIndex(nextArr, a, s);
        System.out.println("起始位置 " + startIndex);
    
        String substring = a.substring(startIndex);
        System.out.println(substring);
        System.out.println(s);
        System.out.println(Objects.equals(substring,s));
    
    }
    
    public static int getStartIndex(int next[], String a, String s) {
        int i = 0;
        int j = 0;
        while (i < a.length() && j < s.length()) {
            if (j == -1 || a.charAt(i) == s.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        
        if (j >= s.length()) {
            return i - s.length();
        } else {
            return -1;
        }
    }
    
    public static int[] getNextArr(String s) {
        int[] next = new int[s.length() + 1];
        int k = -1;
        int j = 0;
        next[0] = -1;
        while (j < s.length()) {
            if (k == -1 || s.charAt(j) == s.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
