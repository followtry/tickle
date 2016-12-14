package cn.followtry.hadoop.demo.hdfs;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 *  brief-hadoop-demo/cn.followtry.hadoop.demo.hdfs.HDFSOperTest
 * @author 
 *		jingzz 
 * @since 
 *		2016年12月14日 上午9:07:50
 */
public class HDFSOperTest {
	
	private String outpathDir = "/user/root/output";

	@Test
	public void testRmDir(){
		try {
			boolean rmExistsOutputDir = HDFSOper.rmExistsOutputDir(outpathDir);
			Assert.assertTrue(rmExistsOutputDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRecListDir(){
		try {
			int actualNum = HDFSOper.recDirListInfo("/");
			Assert.assertNotEquals(0, actualNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
