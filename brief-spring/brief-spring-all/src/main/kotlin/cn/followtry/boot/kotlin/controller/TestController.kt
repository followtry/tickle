package cn.followtry.boot.kotlin.controller

import cn.followtry.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Description
 * @author jingzhongzhi
 * @since  2019/10/18
 */
@RestController
@RequestMapping("kotlin")
open class TestController {

    @Autowired
    lateinit var user : User

    @RequestMapping(value = ["user"],produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getUser(): Any {
        return user.userInfo()
    }
}

