package cn.followtry.incubate;

/**
 * 自写红黑二叉树，先实现二叉树的结构，再实现红黑二叉树的左右旋转。
 * Created by followtry on 2017/4/1.
 */
public class RBTree {

	private RBNode root;

	private Integer size;

	private Integer height;

	private static final Integer ONE = Integer.valueOf(1);

	public void addNode(RBNode node) {
		if (node == null) {
			return;
		}
		if (root == null) {
			root=node;
			size=1;
		} else {
			appendNode(node);
		}
	}

	/**
	 * 循环方式添加节点
	 * @param node 要添加的节点
	 * @return 存在该节点返回原有节点，否则返回null
	 */
	private RBNode appendNode(RBNode node) {
		RBNode curNode=this.root;
		while (true) {

			RBNode left=curNode.getLeft();
			RBNode right=curNode.getRight();
			if (curNode.getData() == node.getData()) {
				//重复数据，不进行添加操作
				return curNode;
			} else if (curNode.getData() > node.getData()) {//左树
				if (left == null) {
					size++;
					curNode.setLeft(node);
					node.setParent(curNode);
					return left;
				}
				curNode=left;
			} else {
				if (right == null) {
					size++;
					curNode.setRight(node);
					node.setParent(curNode);
					return right;
				}
				curNode=right;
			}

		}
	}


	/**
	 * 添加节点(递归方式添加节点)
	 *
	 * @param parent
	 * @param node
	 */
	private void addNode(RBNode parent, RBNode node) {
		RBNode left=parent.getLeft();
		RBNode right=parent.getRight();
		if (parent.getData() > node.getData()) {//转到左树
			if (parent.getLeft() == null) {//设置为左子节点
				size++;
				parent.setLeft(node);
				node.setParent(parent);
			} else {//继续沿着左树查找
				addNode(parent.getLeft(), node);
			}
		} else {//转到右树
			if (parent.getRight() == null) {//设置为右子节点
				size++;
				parent.setRight(node);
				node.setParent(parent);
			} else {//继续沿着右树查找
				addNode(parent.getRight(), node);
			}

		}
	}

	/**
	 * 获取高度
	 * @return
	 */
	public Integer getHeight() {
		if (this.root == null) {
			return Integer.valueOf(0);
		}

		return getSubTreeHeight(this.root);
	}

	private Integer getSubTreeHeight(RBNode node) {
		if (node == null) {
			return Integer.valueOf(0);
		}
		Integer subLeftTreeHeight=getSubTreeHeight(node.getLeft());
		Integer subRightTreeHeight=getSubTreeHeight(node.getRight());

		Integer max=Math.max(subLeftTreeHeight, subRightTreeHeight) + ONE;
		node.setHeight(max);
		return max;
	}

	public Integer getSize() {
		return size;
	}

	public RBNode getRoot() {
		return root;
	}

	/**
	 * 获取根节点
	 *
	 * @param node
	 * @return
	 */
	public RBNode getRoot(RBNode node) {
		if (node == null) {
			return null;
		}
		RBNode tempNode=node;
		RBNode tempParentNode=null;
		while (true) {
			if (tempNode != null) {
				tempParentNode=getParentNode(tempNode);
			}
			if (tempParentNode == tempNode) {
				return tempNode;
			} else {
				tempNode=tempParentNode;
			}
		}
	}

	public RBNode find(int data) {
		RBNode curNode=this.root;
		if (curNode != null) {

			while (curNode.getData() != data) {

				if (curNode.getData() > data) {
					curNode=curNode.getLeft();
				} else if (curNode.getData() < data) {
					curNode=curNode.getRight();
				}

				if (curNode == null) {
					return null;
				}

			}
			return curNode;
		}
		return null;
	}

	/**
	 * 先序遍历
	 */
	public void preorder() {
		if (this.root != null) {
			System.out.print(root.getData()+" ");
			preorder(this.root.getLeft());
		}
	}

	private void preorder(RBNode node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preorder(node.getLeft());
			preorder(node.getRight());
		}
	}

	public void deleteNode(RBNode node) {

	}

	/**
	 * 获取父节点
	 *
	 * @param node
	 * @return
	 */
	public RBNode getParentNode(RBNode node) {
		if (node == null) {
			return null;
		}
		return node.getParent() == null ? node : node.getParent();
	}


	private enum TreeColor {
		RED,
		BLACK;
	}

	public static class RBNode {
		private int data;
		private TreeColor color;
		private RBNode left;
		private RBNode right;
		private RBNode parent;
		private Integer height;

		RBNode(int data, TreeColor color, RBNode left, RBNode right, RBNode parent) {
			this.data=data;
			this.color=color;
			this.left=left;
			this.right=right;
			this.parent=parent;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data=data;
		}

		public TreeColor getColor() {
			return color;
		}

		public void setColor(TreeColor color) {
			this.color=color;
		}

		public RBNode getLeft() {
			return left;
		}

		public void setLeft(RBNode left) {
			this.left=left;
		}

		public RBNode getRight() {
			return right;
		}

		public void setRight(RBNode right) {
			this.right=right;
		}

		public RBNode getParent() {
			return parent;
		}

		public void setParent(RBNode parent) {
			this.parent=parent;
		}

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height=height;
		}

		@Override
		public String toString() {
			return "RBNode{" +
					"data=" + data +
					", color=" + color +
					", left=" + (left == null ? null : left.getData()) +
					", right=" + (right == null ? null : right.getData()) +
					", parent=" + (parent == null ? null : parent.getData()) +
					", height=" + height +
					'}';
		}
	}
}
