package cn.followtry.boot.kotlin.controller

import cn.followtry.boot.java.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Description
 * @author jingzhongzhi
 * @since  2019/10/18
 */
//注解使用方式同 java
@RestController
@RequestMapping("kotlin")
open class TestKotlinController {

    @Autowired
    lateinit var applicationService: ApplicationService

    @RequestMapping(value = ["application"],produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getApplication(): Any {
        return applicationService.beanDefinitionNames
    }
}

