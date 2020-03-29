package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @since 2020/3/2
 */
public class SearchData {
    
    
    /**
     * main.
     */
    public static void main(String[] args) {
//        int nums[] = {1,2,3,3,4,5,6,7,8,9};
        int nums[] = {3};
        int targetNum = 3;
        int index = searchNum(nums,0,nums.length - 1,targetNum);
        System.out.println(index);
    }
    
    public static int searchNum(int[] nums,int left,int right,int targetNum){
        if (left > right) {
            return -1;
        }
        
        int mid = (left + right) / 2;
        int value = nums[mid];
        if (mid == 0 && value == targetNum) {
            return mid;
        } else if (value == targetNum && nums[mid - 1] < targetNum){
            return mid;
        }
        
        if (value >= targetNum){
            int leftIndex = searchNum(nums,left,mid -1,targetNum);
            if (leftIndex != -1){
                return leftIndex;
            }
        }
        
        if (value < targetNum){
            int rightIndex = searchNum(nums,mid + 1,right,targetNum);
            if (rightIndex != -1){
                return rightIndex;
            }
        }
        return -1;
    }
    
}
