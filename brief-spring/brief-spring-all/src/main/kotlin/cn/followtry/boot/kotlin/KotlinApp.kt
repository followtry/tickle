package cn.followtry.boot.kotlin

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Description
 * @author jingzhongzhi
 * @since  2019/10/18
 */

@SpringBootApplication
@ComponentScan("cn.followtry.boot.kotlin")
@RestController
open class KotlinApp {
    @RequestMapping(value = ["/"],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun home(): Any {
        return "this is home!"

    }
}

fun main(args: Array<String>) {
    runApplication<KotlinApp>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}