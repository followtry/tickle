import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author jingzhongzhi
 * @since 2020/2/5
 */
public class TimerWheelDemo {
    
    /**
     * main.
     */
    public static void main(String[] args) {
        HashedWheelTimer wheelTimer = new HashedWheelTimer();
        
        
        MyTimerTask myTimerTask = new MyTimerTask("name1");
        MyTimerTask myTimerTask2 = new MyTimerTask("name2");
        Timeout timeout = wheelTimer.newTimeout(myTimerTask, 5, TimeUnit.SECONDS);
        Timeout timeout2 = wheelTimer.newTimeout(myTimerTask2, 7, TimeUnit.SECONDS);
        System.out.println("结束:" + System.currentTimeMillis() / 1000);
        
    }
    
    static class MyTimerTask implements TimerTask {
        
        private String name;
        
        public MyTimerTask(String name) {
            this.name = name;
        }
        
        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("[" + System.currentTimeMillis() / 1000 + "]MyTimerTask " + name);
        }
    }
}
