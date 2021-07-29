package cn.followtry

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.EnableAspectJAutoProxy

/**
 * kotlin和java的使用对比 https://zhuanlan.zhihu.com/p/164424792
 * @author followtry
 * @since  2021/7/20 6:05 下午
 */
@SpringBootApplication(
    scanBasePackages = ["cn.followtry"],
)
@EnableAspectJAutoProxy
//@EnableTransactionManagement
class KotlinApplication {

}

fun main(args: Array<String>) {
    println("starting springboot in kotlin")
    var ac = SpringApplication.run(KotlinApplication::class.java, *args)
    ac.apply {
        println("say hello")
    }
    println("started springboot in kotlin")
}