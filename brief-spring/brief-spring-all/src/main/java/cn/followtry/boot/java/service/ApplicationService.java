package cn.followtry.boot.java.service;

import cn.followtry.boot.java.mybatis.UserDO;
import cn.followtry.boot.java.mybatis.UserMapper;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author jingzhongzhi
 * @Description
 * @since 2019/10/30
 */
@Service
public class ApplicationService implements ApplicationContextAware,InitializingBean{

    private ApplicationContext applicationContext;

    @Autowired
    private Map<String,MyService> myServiceMap;

    @Autowired
    private Optional<MyService> myServiceOptional;
    
    @Autowired
    private UserMapper userMapper;

    @PostConstruct
    public void init(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(JSON.toJSONString(beanDefinitionNames));
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (myServiceMap != null) {
            System.out.println("ApplicationService的 map 已经注入");
        } else {
            System.out.println("ApplicationService的 map 未注入");
        }

        if (myServiceOptional.isPresent()) {
            System.out.println("ApplicationService的 myServiceOptional 已经注入");
        }
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public List<String> getBeanDefinitionNames() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        return Lists.newArrayList(beanDefinitionNames);
    }

    public Object getUser(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
    
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Object addUser(String name,Integer age){
        UserDO userDO = new UserDO();
        userDO.setName(name);
        userDO.setAge(age);
        userDO.setDate(new Date());
        int insert = userMapper.insert(userDO);
        return userDO;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行 ApplicationService的 InitializingBean");
        for (Map.Entry<String, MyService> entry : myServiceMap.entrySet()) {
            System.out.println("ApplicationService打印注入的 Map：" + entry.getKey());
        }
    }
}
