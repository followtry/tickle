package cn.followtry;

import cn.followtry.aop.MyAopLog;
import org.springframework.stereotype.Service;

/**
 * @author followtry
 * @since 2021/7/22 11:21 上午
 */
@Service
public class HelloServiceImpl implements HelloService{

    @MyAopLog
//    @Transactional(rollbackFor = Exception.class)
    @Override
    public String say(String content) {
        System.out.println("this say:" + content);
        return "say: " + content;
    }
}
