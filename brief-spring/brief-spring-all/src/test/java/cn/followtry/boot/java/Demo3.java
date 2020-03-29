package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @since 2020/2/23
 */
public class Demo3 {
    /**
     * main.
     */
    public static void main(String[] args) {
    
        int[] gas = {3,3,4};
        int[] cost = {3,4,4};
        int i = canCompleteCircuit(gas, cost);
        System.out.println(i);
    
    }
    
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int i =0;
        int start = 0;
        int sum = 0;
        while(true) {
            if(i >= gas.length){
                i = 0;
            }
            
            sum += gas[i];
            int c = cost[i];
            if(sum - c < 0){
                sum = 0;
                start++;
                i= start;
                if (start == gas.length) {
                    return -1;
                }
            } else {
                sum -= c;
                i++;
                if(i == start || (start == 0 && i == gas.length)){
                    break;
                }
            }
        }
        return start;
    }
}
