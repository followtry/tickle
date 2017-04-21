package cn.followtry.hadoop.demo.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.util.InputMismatchException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * brief-hadoop-demo/cn.followtry.hadoop.demo.hdfs.HDFSOper
 * 
 * @author jingzz
 * @since 2016年12月14日 上午10:26:23
 */
public class HDFSOper {

	private static final Logger LOGGER = LoggerFactory.getLogger(HDFSOper.class);

	private static Configuration config = new Configuration();

	private static FileSystem fs;

	static String url = "webhdfs://h2m1:50070";

	static {
		try {
			fs = FileSystem.get(URI.create(url), config);
		} catch (IOException e) {
			LOGGER.error("fs连接异常", e);
			try {
				fs = FileSystem.get(URI.create(url), config);
			} catch (IOException e2) {
				LOGGER.error("fs连接重试异常", e2);
			}
		}
	}

	private HDFSOper() {
	}

	/**
	 * 删除指定的目录
	 * 
	 * @author jingzz
	 * @param outpathDir
	 *            指定要删除的目录路径
	 * @return 删除返回true,不存在或没有删除返回false
	 * @throws FileNotFoundException
	 *             文件未找到异常
	 * @throws IOException
	 *             IO异常
	 */
	public static boolean rmExistsOutputDir(String outpathDir) throws IOException {
		boolean hasDel = false;
		Path output = new Path(outpathDir);
		
		if (hasDel = fs.exists(output)) {
			if (!fs.isDirectory(output)) {
				throw new InputMismatchException("请求路径" + outpathDir + "不是一个目录");
			}
			LOGGER.info("目录{}已经存在,正在删除...", outpathDir);
			if (hasDel = fs.delete(output, true)) {
				LOGGER.info("目录{}已经删除", outpathDir);
			} else {
				LOGGER.info("目录{}删除失败", outpathDir);

			}
		} else {
			LOGGER.info("目录{}不存在", outpathDir);
		}
		return hasDel;
	}

	/**
	 * 创建文件或目录
	 * 
	 * @author jingzz
	 * @param path
	 * @param isDir
	 * @param overwrite
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static boolean createFileOrDir(String path, boolean isDir, boolean overwrite)
			throws IllegalArgumentException, IOException {
		Path realPath = new Path(path);
		boolean isSuccess = false;
		if (isDir) {// 创建目录
			if (fs.exists(realPath)) {
				throw new FileAlreadyExistsException(getFullQualifiedName(realPath));
			} else {
				boolean mkdirs = fs.mkdirs(realPath);
				if (mkdirs) {
					LOGGER.info("目录{}创建成功", getFullQualifiedName(realPath));
				} else {
					LOGGER.info("目录{}创建失败", path);
				}
				isSuccess = mkdirs;
			}
		} else {// 创建文件
			if (overwrite) {
				FSDataOutputStream file = fs.create(realPath, overwrite);
				file.close();
				LOGGER.info("文件{}创建成功", getFullQualifiedName(realPath));
				isSuccess = true;
			} else if (fs.exists(realPath)) {
				LOGGER.info("文件{}已经存在", getFullQualifiedName(realPath));
				throw new FileAlreadyExistsException(getFullQualifiedName(realPath));
			} else {
				isSuccess = fs.createNewFile(realPath);
				if (isSuccess) {
					LOGGER.info("文件{}创建成功", getFullQualifiedName(realPath));
				} else {
					LOGGER.info("文件{}创建失败", path);
				}
			}
		}
		return isSuccess;
	}

	/**
	 * 删除文件 或目录
	 * 
	 * @author jingzz
	 * @param path
	 * @param recursive
	 * @return
	 * @throws IOException
	 */
	public static boolean delFileOrDir(String path, boolean recursive) throws IOException {
		Path realPath = new Path(path);
		boolean isSuccess = false;
		if (fs.isDirectory(realPath)) {
			if (fs.exists(realPath)) {
				LOGGER.info("目录{}已经删除", getFullQualifiedName(realPath));
				isSuccess = fs.delete(realPath, recursive);
			} else {
				LOGGER.info("目录{}不存在", path);
				throw new FileNotFoundException(path);
			}
		} else {
			if (fs.exists(realPath)) {
				LOGGER.info("文件{}已经删除", getFullQualifiedName(realPath));
				isSuccess = fs.delete(realPath, true);
			} else {
				LOGGER.info("文件{}不存在", path);
				throw new FileNotFoundException(path);
			}
		}
		return isSuccess;
	}

	/**
	 * 获取文件全限定名
	 * 
	 * @author jingzz
	 * @param realPath
	 * @return
	 * @throws IOException
	 */
	public static String getFullQualifiedName(Path realPath) throws IOException {
		String fullQualName;
		try {
			fullQualName = fs.resolvePath(realPath).toString();
		} catch (Exception e) {
			fullQualName = realPath.getName();
		}
		return fullQualName;
	}

	/**
	 * 递归显示目录下的文件信息列表
	 * 
	 * @author jingzz
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static int recDirListInfo(String path) throws FileNotFoundException, IOException {
		int fileNum = 0;
		int level = 0;
		fileNum += listFiles(fs, path, level);
		System.out.println("-----------------------------------------------------");
		LOGGER.info("总文件数为：{}个", fileNum);
		System.out.println("总文件数为：" + fileNum + "个");
		return fileNum;
	}

	private static int listFiles(FileSystem fs, String path, int level) throws FileNotFoundException, IOException {
		int fileNum = 0;
		String tempPath = path;
		StringBuilder prefix = new StringBuilder();
		if (level > 0) {
			for (int i = 0; i < level; i++) {
				prefix.append("    ");
			}
		}
		FileStatus[] listStatus = fs.listStatus(new Path(tempPath));
		for (FileStatus f : listStatus) {
			String dir = f.isDirectory() ? "目录" : "文件";
			String name = f.getPath().getName();
			String realPath = f.getPath().toString();
			System.out.println((level + 1) + "层：" + dir + ":" + name);
			//LOGGER.info((level + 1) + "层：" + dir + ":" + name);

			System.out.println(prefix + "路径:" + realPath);
			//LOGGER.info(prefix + "路径:" + realPath);

			System.out.println(prefix + "访问时间：" + f.getAccessTime());
			//LOGGER.info(prefix + "访问时间：" + f.getAccessTime());

			System.out.println(prefix + "块大小:" + f.getBlockSize());
			//LOGGER.info(prefix + "块大小:" + f.getBlockSize());

			System.out.println(prefix + "所属组:" + f.getGroup());
			//LOGGER.info(prefix + "所属组:" + f.getGroup());

			System.out.println(prefix + "长度:" + f.getLen());
			//LOGGER.info(prefix + "长度:" + f.getLen());

			System.out.println(prefix + "修改时间:" + f.getModificationTime());
			//LOGGER.info(prefix + "修改时间:" + f.getModificationTime());

			System.out.println(prefix + "所有者:" + f.getOwner());
			//LOGGER.info(prefix + "所有者:" + f.getOwner());

			System.out.println(prefix + "权限:" + f.getPermission());
			//LOGGER.info(prefix + "权限:" + f.getPermission());

			System.out.println(prefix + "副本:" + f.getReplication());
			//LOGGER.info(prefix + "副本:" + f.getReplication());

			System.out.println(prefix + "============================================================");
			//LOGGER.info(prefix + "============================================================");

			if (f.isDirectory()) {
				fileNum += listFiles(fs, realPath, level + 1);
			} else {
				fileNum++;
			}
		}
		return fileNum;
	}
	
	/**
	 * 重命名文件或目录
	 * @author jingzz
	 * @param srcPath
	 * @param dstPath
	 * @return
	 * @throws IOException
	 */
	public static boolean renameFileOrDir(String srcPath, String dstPath) throws IOException{
		Path src = new Path(srcPath);
		Path dst = new Path(dstPath);
		boolean hasRename = fs.rename(src, dst);
		return hasRename;
	}

}
