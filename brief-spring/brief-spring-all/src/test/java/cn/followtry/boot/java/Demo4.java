package cn.followtry.boot.java;

/**
 * @author jingzhongzhi
 * @since 2020/2/24
 */
public class Demo4 {
    
    static class TreeNode {
         volatile int val;
        TreeNode left;
        TreeNode right;
        
    
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    static int dis = 0;
    
    static TreeNode midNode = new TreeNode(0);
    
    /**
     * main.
     */
    public static synchronized void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.left.right.right = new TreeNode(7);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(6);
    
    
        int p = 7;
        int q = 6;
        int pLen = recurseTree(treeNode, p);
        int qLen = recurseTree(treeNode, q);
        recurseTree(treeNode,p,q);
        int midLen = recurseTree(treeNode, midNode.val);
        System.out.println(qLen + pLen - 2 * midLen);
    }
    
    public static boolean  recurseTree(TreeNode curNode,int p,int q){
        if (curNode == null) {
            return false;
        }
        
        int mid = curNode.val == p || curNode.val == q ? 1 : 0;
        int left = recurseTree(curNode.left, p, q) ? 1 : 0;
        int right = recurseTree(curNode.right, p, q) ? 1 : 0;
    
        if (mid + left + right == 2) {
            midNode = curNode;
        }
        return mid + left + right > 0;
    }
    
    public static int  recurseTree(TreeNode curNode,int num){
            if(curNode == null){
                return -1;
            }
            
            if (curNode.val == num) {
                return 0;
            }
        
            int level = recurseTree(curNode.left,num);
            if (level == -1){
                level = recurseTree(curNode.right,num);
            }
    
            if (level != -1) {
                level++;
            }
            return level;
    }
}
