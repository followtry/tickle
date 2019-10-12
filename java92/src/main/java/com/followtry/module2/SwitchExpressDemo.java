package com.followtry.module2;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/12
 */
public class SwitchExpressDemo {
    /**
     * main.
     */
    public static void main(String[] args) {
        var num = 1;
        var name = switch (num) {
            case 1 -> "张三";
            case 2 -> "李四";
            case 3 -> "王五";
            case 4 -> "马六";
            case 5 -> "常七";
            default -> "哈哈哈";
        };

        System.out.println(name);
    }
}
