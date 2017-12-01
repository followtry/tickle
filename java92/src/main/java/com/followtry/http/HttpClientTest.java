package com.followtry.http;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by jingzhongzhi on 2017/9/15.
 */
public class HttpClientTest {
    
    private static ExecutorService service = Executors.newFixedThreadPool(3);
    
    /** main. */
    public static void main(String[] args) {
    
        Future<Object> submit = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
            
                return null;
            }
        });
    
    }
}
