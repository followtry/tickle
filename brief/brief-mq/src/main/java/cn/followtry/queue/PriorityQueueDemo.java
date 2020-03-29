package cn.followtry.queue;

import java.util.PriorityQueue;

/**
 * @author jingzhongzhi
 * @since 2020/2/3
 */
public class PriorityQueueDemo {
    /**
     * main.
     */
    public static void main(String[] args) {
        PriorityQueue<PriorityElement> queue = new PriorityQueue<>();
        
        for (int i = 0; i < 10; i++) {
            PriorityElement element = new PriorityElement(i % 6, "elem:" + i);
            queue.offer(element);
        }
    
        while (!queue.isEmpty()){
            PriorityElement poll = queue.poll();
            System.out.println(poll);
        }
    
        System.out.println("结束");
        
    }
    
    static class PriorityElement implements Comparable<PriorityElement> {
        
        private Integer id;
        
        private String msg;
        
        public PriorityElement(Integer id, String msg) {
            this.id = id;
            this.msg = msg;
        }
        
        public Integer getId() {
            return id;
        }
        
        public String getMsg() {
            return msg;
        }
        
        @Override
        public int compareTo(PriorityElement o) {
            return  this.id - o.getId();
        }
    
        @Override
        public String toString() {
            return "PriorityElement{" +
                    "id=" + id +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
