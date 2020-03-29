package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @since 2020/2/26
 */
public class Demo6 {
    
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
//        treeNode.right = new TreeNode(8);
//
//        treeNode.left.left = new TreeNode(11);
//        treeNode.left.left.left = new TreeNode(7);
//        treeNode.left.left.right = new TreeNode(2);
//
//        treeNode.right.left = new TreeNode(13);
//        treeNode.right.right = new TreeNode(14);
//        treeNode.right.right.right = new TreeNode(1);
        boolean hasPathSum = hasPathSum(treeNode, 5);
        System.out.println(hasPathSum);
    }
    
    public static boolean hasPathSum(TreeNode root, int sum) {
        return subPathSum(root,sum);
    }
    
    public static boolean subPathSum(TreeNode node,int sum){
        if(node == null){
            return sum == 0;
        }
    
        int value = node.val;
        sum -= value;
        
        //判断当前为叶子节点
        if(node.left == null && node.right == null) {
            return sum == 0;
        }
        
        return subPathSum(node.left, sum) || subPathSum(node.right,sum);
    }
}
