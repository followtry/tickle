package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @since 2020/2/26
 */
public class Demo7 {
    
    static int num =0;
    
    static class TreeNode {
        volatile int val;
        TreeNode left;
        TreeNode right;
        
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
    /**
     * main.
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(11);
        treeNode.left.right = new TreeNode(7);
        int i = minCameraCover(treeNode);
        System.out.println(i);
    }
    
    public static int minCameraCover(TreeNode root) {
        if(root == null){
            return 0;
        }
        minCameraCover(root,true);
        return num;
    }
    
    public static void minCameraCover(TreeNode root,boolean parentCamera){
        if(root == null){
            return;
        }
        
        boolean curHasCamera;
        if(!parentCamera){
            curHasCamera = true;
            num++;
        } else {
            curHasCamera = false;
        }
        
        if(root.left != null) {
            minCameraCover(root.left,curHasCamera);
        }
        
        if(root.right != null) {
            minCameraCover(root.right,curHasCamera);
        }
    }
}
