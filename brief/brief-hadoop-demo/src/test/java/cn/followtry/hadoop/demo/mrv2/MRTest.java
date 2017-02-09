package cn.followtry.hadoop.demo.mrv2;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.followtry.hadoop.demo.v2.mr.Averager;
import cn.followtry.hadoop.demo.v2.mr.Dedup;
import cn.followtry.hadoop.demo.v2.mr.InvertedIndex;
import cn.followtry.hadoop.demo.v2.mr.MTjoin;
import cn.followtry.hadoop.demo.v2.mr.STjoin;
import cn.followtry.hadoop.demo.v2.mr.Sort;
import cn.followtry.hadoop.demo.v2.mr.WordCountV2;

/**
 * mr编程模型操作的测试用例
 * @author 
 *		jingzz 
 * @since 
 *		
 * @version
 *    
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MRTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MRTest.class);
	
	private static final String basePath = "webhdfs://h2m1:50070/user/root";
	
	private String inputPath;
	
	private String outputPath;
	
	@Test
	// 数据去重
	public void test010Dedup() {
		inputPath = basePath + "/input_dedup";
		outputPath = basePath + "/output_dedup";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			Dedup.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("数据去重异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
	
	@Test
	// 数据排序
	public void test020Sort() {
		inputPath = basePath + "/input_sort";
		outputPath = basePath + "/output_sort";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			Sort.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("数据排序异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
	
	@Test
	//单词统计
	public void test030WordCount() {
		inputPath = basePath + "/input_wc";
		outputPath = basePath + "/output_wc";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			WordCountV2.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("单词统计异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
	
	@Test
	//平均成绩计算
	public void test040Average() {
		inputPath = basePath + "/input_average";
		outputPath = basePath + "/output_average";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			Averager.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("平均成绩计算异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
	
	@Test
	//单表关联
	public void test050STjoin() {
		inputPath = basePath + "/input_st";
		outputPath = basePath + "/output_st";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			STjoin.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("单表关联异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
	
	@Test
	//多表关联
	public void test060MTjoin() {
		inputPath = basePath + "/input_mt";
		outputPath = basePath + "/output_mt";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			MTjoin.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("多表关联异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
	
	@Test
	//倒排索引
	public void test070InvertedIndex() {
		inputPath = basePath + "/input_inverted";
		outputPath = basePath + "/output_inverted";
		String[] args = {inputPath,outputPath};
		boolean res = false;
		try {
			InvertedIndex.main(args);
			res = true;
		} catch (Exception e) {
			LOGGER.error("倒排索引异常",e);
			res = false;
		}
		Assert.assertTrue(res);
	}
}
