package cn.followtry.hadoop.demo.hdfs;

import java.io.IOException;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * brief-hadoop-demo/cn.followtry.hadoop.demo.hdfs.HDFSOperTest
 * 
 * @author jingzz
 * @since 2016年12月14日 上午9:07:50
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HDFSOperTest {

	private String outpathDir = "/user/root/output";

	private String fileName = outpathDir + "/123";

	@Test
	// 创建目录
	public void test001CreateDir() {
		try {
			boolean hasCreated = HDFSOper.createFileOrDir(outpathDir, true, true);
			System.out.println("创建目录【" + outpathDir + "】成功");
			Assert.assertTrue(hasCreated);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	// 创建文件
	public void test002CreateFile() {
		try {
			boolean hasCreated = HDFSOper.createFileOrDir(fileName, false, true);
			System.out.println("创建文件【" + fileName + "】成功");
			Assert.assertTrue(hasCreated);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	// 遍历目录下文件
	public void test003RecListDir() {
		try {
			int actualNum = HDFSOper.recDirListInfo(outpathDir);
			System.out.println("遍历目录【" + outpathDir + "】");
			Assert.assertNotEquals(0, actualNum);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertNotEquals(0, 0);
		}
	}

	@Test
	//删除文件
	public void test004DeleteFile() {
		try {
			boolean deleteFile = HDFSOper.delFileOrDir(fileName, true);
			System.out.println("删除文件【" + fileName + "】成功");
			Assert.assertTrue(deleteFile);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	//删除目录
	public void test005RmDir() {
		try {
			boolean rmExistsOutputDir = HDFSOper.rmExistsOutputDir(outpathDir);
			System.out.println("删除目录【" + outpathDir + "】成功");
			Assert.assertTrue(rmExistsOutputDir);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

}
