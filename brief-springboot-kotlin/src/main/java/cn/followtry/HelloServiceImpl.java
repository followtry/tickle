package cn.followtry;

import cn.followtry.aop.MyAopLog;
import cn.followtry.component.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author followtry
 * @since 2021/7/22 11:21 上午
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Autowired
    private CountryService countryService;

    @MyAopLog
//    @Transactional(rollbackFor = Exception.class)
    @Override
    public String say(String content) {
        System.out.println("this say:" + content);
        return "say: " + content;
    }
}
