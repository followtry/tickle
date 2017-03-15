/**
 * 
 */
package cn.followtry.incubate.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中文校验器
 * @author jingzz
 * @time 2016年10月8日 上午9:39:30
 * @name brief-example/com.yonyou.test.ChineseCheck
 * @since 2016年10月8日 上午9:39:30
 */
public class ChineseCheck {
	
	/**
	 * 计算中文的数量
	 * @author jingzz
	 * @param content
	 * @return
	 */
	public static int chineseCount(String content){
		int count = 0;
		//汉字在unicode中的起止编码
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
			count = count + 1;
			}
		}
		return count;
	}
	
	/**
	 * 判断是否有中文字符
	 * @author jingzz
	 * @param content
	 * @return
	 */
	public static boolean haveChinese(String content){
		int count = chineseCount(content);
		return count > 0;
	}
	
	public static void main(String[] args) {
		String content = "我dsfd";
		int chineseCount = chineseCount(content);
		System.out.println(chineseCount);
		System.out.println(haveChinese(content));
	}
}
