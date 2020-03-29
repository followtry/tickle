package cn.followtry.boot.java;

import com.alibaba.fastjson.JSON;

/**
 * @author jingzhongzhi
 * @since 2020/2/23
 */
public class Demo2 {
    
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    
        @Override
        public String toString() {
            return val+" ";
        }
    }
    
    /**
     * main.
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode listNode = reverseList(head);
        System.out.println(JSON.toJSONString(listNode));
    }
    
    public static ListNode reverseList(ListNode head) {
        ListNode listNode = reverseNode(head, null);
        return listNode;
    }
    
    public static ListNode reverseNode(ListNode node,ListNode rootNode){
        if(node == null){
            return rootNode;
        }
        
        ListNode next = node.next;
        node.next = null;
        ListNode p2 = rootNode;
        rootNode = node;
        rootNode.next = p2;
        
        return reverseNode(next,rootNode);
    }
}
