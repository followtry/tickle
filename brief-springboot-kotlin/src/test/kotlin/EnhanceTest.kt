package cn.followtry

import org.omg.CORBA.Object
import org.springframework.cglib.proxy.*
import java.lang.reflect.Method

/**
 *
 * @author followtry
 * @since  2021/7/27 8:41 下午
 */

open class EnhanceHelloService : HelloService {

    override fun say(content: String?): String? {
        println("EnhanceHelloService say : $content")
        return content;
    }
}

class ProxyFactoryV2 : MethodInterceptor, Callback {
    override fun intercept(proxy: Any?, method: Method?, args: Array<out Any>?, methodProxy: MethodProxy?): Any {
        val res = methodProxy!!.invokeSuper(proxy, args)
        println("ProxyFactoryV2 invoke")
        return res
    }
}
class ProxyFactory : MethodInterceptor, Callback {

    fun createProxy(target: Any): Any? {
        val enhancer = Enhancer()
        enhancer.setSuperclass(target.javaClass)
//        val callbacks = arrayOf(this,ProxyFactoryV2())
        val callbacks = arrayOf(this)
        enhancer.setCallbacks(callbacks)
//        enhancer.setCallbackFilter(HelloCallbackFilter())

        return enhancer.create()
    }

    fun before(beforeContent: String) {
        println("EnhanceInterceptor before exec $beforeContent")
    }

    fun after(afterContent: String) {
        println("EnhanceInterceptor after exec $afterContent")
    }

    override fun intercept(proxy: Any?, method: Method?, args: Array<out Any>?, methodProxy: MethodProxy?): Any {
        before("测试")
        var res = methodProxy!!.invokeSuper(proxy, args)
        after("测试后")
        return res
    }
}

class HelloCallbackFilter : CallbackFilter {
    override fun accept(method: Method?): Int {
        return 0
    }
}

fun main() {

    val enhanceHelloService = EnhanceHelloService()
    val service = ProxyFactory().createProxy(enhanceHelloService) as EnhanceHelloService
    service.say("我是kotlin的cglib代理生成的")
}