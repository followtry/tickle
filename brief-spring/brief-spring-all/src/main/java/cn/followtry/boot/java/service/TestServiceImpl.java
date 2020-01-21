package cn.followtry.boot.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/12/3
 */
@Service
public class TestServiceImpl implements TestService {


    private HelloService  helloService;

    @Autowired
    private List<HelloService> multiServiceList;

    @Autowired
    private Map<String, HelloService> multiServiceMap;

    @Override
    public void sayHello() {
        System.out.println("--------------list--------------");
        for (HelloService multiService : multiServiceList) {
            multiService.sayHello();
        }

        System.out.println("--------------map--------------");
        for (Map.Entry<String, HelloService> entry : multiServiceMap.entrySet()) {
            System.out.println("key=" + entry.getKey());
            entry.getValue().sayHello();
        }
    }
}
