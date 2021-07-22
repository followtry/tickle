package cn.followtry

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 *
 * @author followtry
 * @since  2021/7/20 6:05 下午
 */
@SpringBootApplication(scanBasePackages = ["cn.followtry"])
open class KotlinApplication {

}

fun main(args: Array<String>) {
    println("starting springboot in kotlin")
    SpringApplication.run(KotlinApplication::class.java, *args)
    println("started springboot in kotlin")
}