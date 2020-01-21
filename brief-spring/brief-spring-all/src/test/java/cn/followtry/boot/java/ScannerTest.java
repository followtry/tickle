package cn.followtry.boot.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/29
 */
public class ScannerTest {

    /**  */
    private static final String BASE_PACKAGE = "cn.followtry.boot.java";

    /**
     * main.
     */
    public static void main(String[] args) {

        GenericApplicationContext applicationContext = new AnnotationConfigApplicationContext();

//        CustomScanner scanner = new CustomScanner(applicationContext, MyAnno.class);
//        int scanCount = scanner.scan(BASE_PACKAGE);
//        applicationContext.refresh();
//        System.out.println("1扫描的数量"+scanCount);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("bean name : " + beanDefinitionName);
        }

    }
}
