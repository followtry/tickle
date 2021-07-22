package cn.followtry

/**
 *kotlin类的扩展
 * @author followtry
 * @since  2021/7/22 11:17 上午
 */
open class KUserExt(name :String) {
    private var name: String = name

    fun username(): String {
        return name
    }
}

fun KUserExt.say(content: String): String {
    return this.username() + " say:$content"
}

fun main() {
    println(KSingleton.doSomeThing())
    println(KUserExt("followtry").say("hello world"))
}