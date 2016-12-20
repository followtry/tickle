/**
 * 
 */
package cn.followtry.hbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.followtry.hbase.demo.HBaseOper;

/**
 * @author jingzz
 * @time 2016年12月17日 下午12:29:42
 * @func 
 * @name HBaseOperTest
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HBaseOperTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HBaseOperTest.class);
	
	private static Connection conn;

	private static final String tName = "t1";
	
	private static final String cfName = "col1";
	
	private static final String rowKey = "123456";
	
	private static final String testColName = "sex";
	
	static{
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "h2m1,h2s1,h2s2");
		conf.set("hbase.rootdir", "hdfs://h2m1:8220/hbase");
		try {
			conn = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			LOGGER.error("创建HBase连接异常", e);
		}
	}
	
	@Before
	public void setup(){
			LOGGER.info("测试前初始化");
	}
	
	@After
	public void clean(){
		LOGGER.info("测试后清理");
	}
	
	@Test
	public void  test0100CreateTable() throws Exception{
		boolean createTable = HBaseOper.createTable(conn,tName,cfName);
		Assert.assertTrue(createTable);
	}
	
	@Test
	public void  test0200AddData() throws Exception{
		Map<String, String> colVlues = new HashMap<String,String>();
		colVlues.put("id", "59380");
		colVlues.put("name", "jingzz");
		colVlues.put(testColName, "男");
		colVlues.put("age", "24");
		colVlues.put("location", "beijing");
		colVlues.put("id2", "59380");
		colVlues.put("name2", "jingzz");
		colVlues.put("sex2", "男");
		colVlues.put("age2", "24");
		colVlues.put("location2", "beijing");
		boolean addData = HBaseOper.addData(conn,tName, rowKey , cfName, colVlues);
		Assert.assertTrue(addData);
	}
	
	@Test
	public void  test0300GetData() throws Exception{
		List<Cell> data = HBaseOper.getData(conn,tName, rowKey);
		Assert.assertNotNull(data);
	}
	
	@Test
	public void  test0400DeleteData() throws Exception{
		boolean delete = HBaseOper.deleteData(conn, tName, rowKey, cfName, testColName);
		Assert.assertTrue(delete);
	}
	
	@Test
	public void  test0401DisableTable() throws Exception{
		boolean delete = HBaseOper.disableTable(conn, tName);
		Assert.assertTrue(delete);
	}
	
	@Test
	public void  test0402DropTable() throws Exception{
		boolean dropTable = HBaseOper.dropTable(conn,tName);
		Assert.assertTrue(dropTable);
	}
	
	@Test
	public void  test0301ScanTable() throws Exception{
		List<Cell> scan = HBaseOper.scan(conn, tName);
		Assert.assertNotNull(scan);
	}
}
