package cn.followtry.web

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

    @GetMapping(value = ["hello"])
    fun hello(name: String) : String {
        return "hello" + name
    }
}