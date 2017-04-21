package cn.followtry.incubate;

/**
 * Created by followtry on 2017/4/1.
 */
public class RBTreeTest {
	static int i =0;
	public static void main(String[] args) {
		RBTree rbTree=new RBTree();
		RBTree.RBNode rbNode1=new RBTree.RBNode(30, null, null, null, null);
		RBTree.RBNode rbNode2=new RBTree.RBNode(20, null, null, null, null);
		RBTree.RBNode rbNode3=new RBTree.RBNode(40, null, null, null, null);
		RBTree.RBNode rbNode4=new RBTree.RBNode(10, null, null, null, null);
		RBTree.RBNode rbNode5=new RBTree.RBNode(25, null, null, null, null);
		RBTree.RBNode rbNode6=new RBTree.RBNode(35, null, null, null, null);
		RBTree.RBNode rbNode7=new RBTree.RBNode(60, null, null, null, null);
		RBTree.RBNode rbNode8=new RBTree.RBNode(5, null, null, null, null);
		RBTree.RBNode rbNode9=new RBTree.RBNode(22, null, null, null, null);
		RBTree.RBNode rbNode10=new RBTree.RBNode(27, null, null, null, null);
		RBTree.RBNode rbNode11=new RBTree.RBNode(50, null, null, null, null);
		RBTree.RBNode rbNode12=new RBTree.RBNode(70, null, null, null, null);
		RBTree.RBNode rbNode13=new RBTree.RBNode(21, null, null, null, null);
		RBTree.RBNode rbNode14=new RBTree.RBNode(23, null, null, null, null);
		RBTree.RBNode rbNode15=new RBTree.RBNode(26, null, null, null, null);
		RBTree.RBNode rbNode16=new RBTree.RBNode(28, null, null, null, null);
		RBTree.RBNode rbNode17=new RBTree.RBNode(4, null, null, null, null);
		RBTree.RBNode rbNode18=new RBTree.RBNode(3, null, null, null, null);
		RBTree.RBNode rbNode19=new RBTree.RBNode(2, null, null, null, null);
		RBTree.RBNode rbNode20=new RBTree.RBNode(1, null, null, null, null);


		rbTree.addNode(rbNode1);
		rbTree.addNode(rbNode2);
		rbTree.addNode(rbNode3);
		rbTree.addNode(rbNode4);
		rbTree.addNode(rbNode5);
		rbTree.addNode(rbNode6);
		rbTree.addNode(rbNode7);
		rbTree.addNode(rbNode8);
		rbTree.addNode(rbNode9);
		rbTree.addNode(rbNode10);
		rbTree.addNode(rbNode11);
		rbTree.addNode(rbNode12);
		rbTree.addNode(rbNode13);
		rbTree.addNode(rbNode14);
		rbTree.addNode(rbNode15);
		rbTree.addNode(rbNode16);
		rbTree.addNode(rbNode17);
		rbTree.addNode(rbNode18);
		rbTree.addNode(rbNode19);
		rbTree.addNode(rbNode20);
		System.out.println(rbTree.getRoot(rbNode12));
		System.out.println(rbTree.getParentNode(rbNode12));

		rbTree.preorder();
		System.out.println(i);
		System.out.println();
		System.out.println(rbTree.getSize());

		System.out.println(rbTree.find(22));
		System.out.println("高度："+rbTree.getHeight());
	}
}
