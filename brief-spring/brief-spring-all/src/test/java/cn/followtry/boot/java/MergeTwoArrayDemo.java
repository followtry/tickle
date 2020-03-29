package cn.followtry.boot.java;

import com.alibaba.fastjson.JSON;

/**
 * 合并两个有序数组
 * @author jingzhongzhi
 * @since 2020/3/3
 */
public class MergeTwoArrayDemo {
    
    /**
     * main.
     */
    public static void main(String[] args) {
//        int A[] = {1,2,3,0,0,0,0};
        int A[] = {2,0};
//        int B[] = {2,5,6};
        int B[] = {1};
        merge(A,1,B,1);
        System.out.println(JSON.toJSONString(A));
    }
    
    public static void merge(int[] A, int m, int[] B, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int C[] = new int[m+n];
        while (i < m || j < n){
            if(m == 0 && n > 0){
                C[k++] = B[j];
                j++;
            } else if (m > 0 && n == 0){
                C[k++] = A[i];
                i++;
            } else if(i < m && j < n){
                int valueA = A[i];
                int valueB = B[j];
                if(valueA < valueB){
                    C[k++] = valueA;
                    i++;
                } else {
                    C[k++] = valueB;
                    j++;
                }
            } else if (i >= m){
                C[k++] = B[j];
                j++;
            } else if (j >= n){
                C[k++] = A[i];
                i++;
            }
        
        
        }
        for(int x = 0;x < m+n;x++){
            A[x] = C[x];
        }
    }
}
