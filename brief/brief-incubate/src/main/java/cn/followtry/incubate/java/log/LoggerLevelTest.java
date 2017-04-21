package cn.followtry.incubate.java.log;

/**
 ** 测试将日志写入到指定的不同的文件中
 * Created by followtry on 2017/4/18.
 */
public class LoggerLevelTest {


	public static void main(String[] args) {
		try {
			String str=null;
			System.out.println(str.length());
		} catch(Exception e) {
			LoggerUtil.error(true,"指定日志发送位置",e.toString());
		}
	}
}
