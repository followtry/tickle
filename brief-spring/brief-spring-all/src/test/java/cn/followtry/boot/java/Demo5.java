package cn.followtry.boot.java;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingzhongzhi
 * @since 2020/2/24
 */
public class Demo5 {
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
        TreeNode treeNode = new TreeNode(1);
        
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);
    
        treeNode.left.right.right = new TreeNode(7);
    
        List<List<Integer>> lists = levelOrderBottom(treeNode);
        System.out.println(JSON.toJSONString(lists));
    
    }
    
    //后续遍历
    public  static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null){
            return null;
        }
        List<List<Integer>> nodeList = new ArrayList(10);

        levelOrderBottom2(root,0,nodeList);
    
        List<List<Integer>> result = new ArrayList(nodeList.size());
        for (int i = nodeList.size() - 1; i >= 0 ; i--) {
            List<Integer> list = nodeList.get(i);
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            result.add(list);
        }
        return result;
    }
    
    public static void levelOrderBottom2(TreeNode node,int n,List<List<Integer>> nodeList){
        if(node == null){
            return;
        }
        
        nodeList.add(new ArrayList<>());
        
        List<Integer> rootLevel = getOrDefault(nodeList,n);
        rootLevel.add(node.val);
        
        levelOrderBottom2(node.left,n+1,nodeList);
        levelOrderBottom2(node.right,n+1,nodeList);
        
    }
    
    public static List<Integer> getOrDefault(List<List<Integer>> nodeList,int i){
        List<Integer> levelList = nodeList.get(i);
        if(levelList == null){
            levelList = new ArrayList();
            nodeList.set(i,levelList);
        }
        return levelList;
    }
}
