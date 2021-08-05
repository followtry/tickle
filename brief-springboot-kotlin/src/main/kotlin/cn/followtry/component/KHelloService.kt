package cn.followtry.component

import cn.followtry.HelloService
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service

/**
 *
 * @author followtry
 * @since  2021/7/22 11:37 上午
 */
@Service
@ConditionalOnBean(name = ["helloServiceImpl"])
open class KHelloService : HelloService,BeanFactoryAware,ApplicationContextAware{

    @Autowired
    private lateinit var beanFactory: BeanFactory
    private lateinit var ac: ApplicationContext
    @Autowired
    @Qualifier(value = "helloServiceImpl")
    private lateinit var helloService: HelloService

    override fun say(content: String): String {
        return content
    }

    override fun setBeanFactory(bf: BeanFactory) {
        this.beanFactory = bf
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.ac = applicationContext
        val say = helloService.say("我是java的hello实现，被kotlin调用了")
        helloService.say("==================")
        println("khello $say")
    }
}