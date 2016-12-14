package cn.followtry.hadoop.demo.hdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDFSOper {

	private static final Logger LOGGER = LoggerFactory.getLogger(HDFSOper.class);

	public static boolean rmExistsOutputDir(String outpathDir) throws FileNotFoundException, IOException {
		boolean hasDel = false;
		// 将本地文件上传到hdfs。
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(URI.create("webhdfs://h2m1:50070"), config);
		Path output = new Path(outpathDir);
		if (hasDel = fs.exists(output)) {
			LOGGER.info("目录{}已经存在,正在删除...", outpathDir);
			System.out.println("目录" + outpathDir + "已经存在,正在删除...");
			if (hasDel = fs.delete(output, true)) {
				System.out.println("目录" + outpathDir + "已经删除");
				LOGGER.info("目录{}已经删除", outpathDir);
			} else {
				System.out.println("目录" + outpathDir + "删除失败");
				LOGGER.info("目录{}删除失败", outpathDir);

			}
		} else {
			System.out.println("目录" + outpathDir + "不存在");
			LOGGER.info("目录{}不存在", outpathDir);
		}
		return hasDel;
	}
}
