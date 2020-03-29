package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @since 2020/2/23
 */
public class Demo1 {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**
     * main.
     */
    public static void main(String[] args) {
    
        
        Integer[] a = {5,1,7,null,2,6,9};
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(7);
        root.left.left = null;
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
    
        boolean validBST = isValidBST(root);
        System.out.println(validBST);
    }
    
    public static boolean isValidBST(TreeNode root) {
        
//        boolean ans = dt(root,root.val,root.val);
        boolean ans = dt(root,null,null);
        return ans;
    }
    
    public static boolean dt(TreeNode node,Integer lower,Integer upper){
        if(node == null) {
            return true;
        }
        
        int value = node.val;
        if (lower != null && value <= lower) return false;
        if (upper != null && value >= upper) return false;
        
        if (!dt(node.right,value,upper)) return false;
        if (!dt(node.left,lower,value)) return false;
        return true;
    }
    
}
