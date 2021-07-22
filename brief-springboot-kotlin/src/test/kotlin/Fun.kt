package cn.followtry

/**
 *
 * @author followtry
 * @since  2021/7/21 4:24 下午
 */
//完整的函数
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum1(a: Int, b: Int) = a + b

public fun sum2(a: Int, b: Int): Int = a + b

fun main(args: Array<String>) {
    println(sum(1,2))
    println(sum1(1,3))
    println(sum2(1,4))
}