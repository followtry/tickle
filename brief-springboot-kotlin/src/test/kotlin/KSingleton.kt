package cn.followtry

import java.io.Serializable

/**
 *
 * @author followtry
 * @since  2021/7/22 10:54 上午
 */
object KSingleton : Serializable {

    fun doSomeThing(): String {
        return "I'm a singleton object"
    }

    private fun readResolve(): Any {
        return KSingleton
    }
}