package cn.followtry;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/30
 */
@Configuration
@ConditionalOnProperty(
        name = {"mdp.zebra.enabled"},
        matchIfMissing = true
)
public class ScanAutoConfiguration {

    @Bean
    public ScannerPostProcessor myScannerPostProcessor(){
        return new ScannerPostProcessor();
    }
}
