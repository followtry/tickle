package cn.followtry

import org.assertj.core.util.Lists
import java.util.ArrayList

/**
 *
 * @author followtry
 * @since  2021/7/21 5:55 下午
 */

fun sum(vararg v: Int) : Int {
    var sum = 0;
    for (item in v) {
        sum += item;
    }
    return sum;
}
var desc = """
    这是一段文字描述，描述的是语言的特性系那个管
    
"""

fun main() {
    println(sum(1,2,3,4,5))
    //lambda表达式
    val sumLambda : (Int,Int) -> Int = {x,y -> x + y}
    println(sumLambda(1,4))

    println(desc)
}