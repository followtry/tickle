package cn.followtry.web

import cn.followtry.HelloService
import cn.followtry.model.UserInfo
import cn.followtry.model.UserInfo2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author followtry
 * @since  2021/7/20 6:16 下午
 */
@RestController
@RequestMapping(value = ["ws/kotlin/web"])
open class TestController {

    @Autowired
    @Qualifier(value = "KHelloService")
    private lateinit var helloService : HelloService

    @GetMapping(value = ["hello"])
    fun hello(name: String,content : String?) : String {
        val u = UserInfo(name,29,content?:"默认的内容")
        val u2 = UserInfo2(name,29,content?:"默认的内容")
        return "$name ${helloService.say(content?:"")} $u $u2"
    }
}