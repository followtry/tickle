package cn.followtry.web

import cn.followtry.HelloService
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
    fun hello(name: String,content : String) : String {
        return "$name ${helloService.say(content)}"
    }
}