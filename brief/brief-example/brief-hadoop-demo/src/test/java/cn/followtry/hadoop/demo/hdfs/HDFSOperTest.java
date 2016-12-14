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
	
	private String newOutpathDir = "/user/root/output2";

	private String fileName = outpathDir + "/123";
	
	private String newFileName = outpathDir + "/456";

	@Test
	// 创建目录
	public void test010CreateDir() {
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
	public void test011RenameDir() throws IOException {
		String srcPath = outpathDir; 
		String dstPath = newOutpathDir;
		boolean hasRename = HDFSOper.renameFileOrDir(srcPath, dstPath);
		Assert.assertTrue(hasRename);
	}

	@Test
	public void test012RenameDir() throws IOException {
		String srcPath = newOutpathDir; 
		String dstPath = outpathDir;
		boolean hasRename = HDFSOper.renameFileOrDir(srcPath, dstPath);
		Assert.assertTrue(hasRename);
	}
	
	@Test
	// 创建文件
	public void test020CreateFile() {
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
	public void test021RenameFile() throws IOException {
		String srcPath = fileName; 
		String dstPath = newFileName;
		boolean hasRename = HDFSOper.renameFileOrDir(srcPath, dstPath);
		Assert.assertTrue(hasRename);
	}

	@Test
	public void test022RenameFile() throws IOException {
		String srcPath = newFileName; 
		String dstPath = fileName;
		boolean hasRename = HDFSOper.renameFileOrDir(srcPath, dstPath);
		Assert.assertTrue(hasRename);
	}

	@Test
	// 遍历目录下文件
	public void test030RecListDir() {
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
	public void test040DeleteFile() {
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
	public void test050RmDir() {
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
