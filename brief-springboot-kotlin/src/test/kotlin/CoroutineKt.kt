package cn.followtry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author followtry
 * @since  2021/7/28 3:25 下午
 */
class CoroutineKt {
}

fun main(args: Array<String>) {
    GlobalScope.launch {
        delay(3000)
        println("${Thread.currentThread().name} world")
    }

    GlobalScope.launch {
        delay(3000)
        println("${Thread.currentThread().name} world")
    }
    println("${Thread.currentThread().name} Hello,") // main function continues while coroutine is delayed
    Thread.sleep(20000L)
}