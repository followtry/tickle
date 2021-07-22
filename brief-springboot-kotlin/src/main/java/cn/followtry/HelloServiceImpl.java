package cn.followtry;

import org.springframework.stereotype.Service;

/**
 * @author followtry
 * @since 2021/7/22 11:21 上午
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public String say(String content) {
        return "say: " + content;
    }
}
