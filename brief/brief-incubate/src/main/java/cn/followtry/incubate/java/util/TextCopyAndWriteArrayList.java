package cn.followtry.incubate.java.util;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写入并复制
 * 
 * 添加操作多时效率低，每次添加时都会复制，开销非常大。并发迭代多时可以使用，提高效率。
 * @author 
 *		jingzz 
 * @since 
 *		
 * @version
 *    
 */
public class TextCopyAndWriteArrayList {
	
	private static int a = 12;
	
	public static void main(String[] args) {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		/**
		 * 每次增加数据都会使用ReentrantLock将内部存储数组A锁住，并将数组复制出一份新的作为数组B，B比A长1，然后将新的值放在B中，然后将A指向B，
		 * 即A原来的数据就废弃掉了。
		 * 
		 * 如果频繁的调用add方法，该list的性能会大打折扣，因为需要频繁的复制数组
		 * 
		 * 注意：与添加类似的还有删除
		 */
		list.add("123");
		
		int b = a;
		
		TextCopyAndWriteArrayList.a++;
		/**
		 * 对于多个需要添加的元素，可以通过调用addAll(collection),添加集合的方式。使得数组只复制一次，扩展collection个长度
		 */
		list.addAll(list);
		
		list.remove("123");
	}
}
