
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @Description
 * @author jingzhongzhi
 * @since  2019/10/18
 */

@SpringBootApplication
@ComponentScan("cn.followtry.boot.kotlin")
open class KotlinApp

fun main(args: Array<String>) {
    runApplication<KotlinApp>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}