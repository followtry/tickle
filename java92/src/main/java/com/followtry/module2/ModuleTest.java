package com.followtry.module2;

import cn.followtry.module.Module1;
import cn.followtry.module.Module2;
import java.io.IOException;
import java.net.URI;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

/**
 * Hello world!
 *
 */

public class ModuleTest
{
    public static void main( String[] args )
    {
        //在java90中被export的类
        Module1.display();
        Module2.display();
        //模块化中未被export的包中的类，在引用时会被编译器报错。
//        Module3.display();
    
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();
    
        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandler.asString());
            
            int i = response.statusCode();
            String body = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
    
    }
}
