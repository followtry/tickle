package cn.followtry.hbase.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * brief-hadoop-demo/cn.followtry.hbase.demo.HBaseOper
 * 
 * @author jingzz
 * @since 2016年12月16日 下午3:30:11
 */
public class HBaseOper {

	private static final Logger LOGGER = LoggerFactory.getLogger(HBaseOper.class);

	public static List<Cell> getData(Connection conn,String tName,String rowKey) throws IOException{
		TableName tableName = TableName.valueOf(tName);
		Table table = conn.getTable(tableName);
		Get get = new Get(Bytes.toBytes(rowKey));
		Result result = table.get(get);
		List<Cell> listCells = result.listCells();
		for (Cell cell : listCells) {
			LOGGER.info("===============================================");
			LOGGER.info("表名：{}",tName);
			LOGGER.info("行健：{}",Bytes.toString(CellUtil.cloneRow(cell)));
			LOGGER.info("列族：{}",Bytes.toString(CellUtil.cloneFamily(cell)));
			LOGGER.info("列名：{}",Bytes.toString(CellUtil.cloneQualifier(cell)));
			LOGGER.info("值：{}",Bytes.toString(CellUtil.cloneValue(cell)));
		}
		LOGGER.info("===============================================");
		return listCells;
	}

	public static boolean addData(Connection conn,String tName, String rowKey, String colFamName, Map<String, String> colVlues)
			throws IOException {
		if (colVlues == null || colVlues.size() == 0) {
			return false;
		}
		TableName tableName = TableName.valueOf(tName);
		Table table = conn.getTable(tableName);
		Put put = new Put(Bytes.toBytes(rowKey));
		// 获取列族
		HColumnDescriptor[] columnFamilies = table.getTableDescriptor().getColumnFamilies();
		for (HColumnDescriptor columnDescriptor : columnFamilies) {
			String cfName = columnDescriptor.getNameAsString();
			if (cfName != null && cfName.equals(colFamName)) {//能匹配上列族名称
				if (colVlues != null && colVlues.size() > 0) {
					for (Map.Entry<String, String> entry : colVlues.entrySet()) {
						put.addColumn(Bytes.toBytes(cfName), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
					}
				}
			}
		}
		table.put(Arrays.asList(put));
		LOGGER.info("数据添加成功");
		return true;
	}

	/**
	 * @author jingzz
	 * @throws IOException
	 */
	public static boolean dropTable(Connection conn,String tName) throws IOException {
		Admin admin = conn.getAdmin();
		TableName tableName = TableName.valueOf(tName);
		if (admin.tableExists(tableName)) {
			LOGGER.info("表【{}】已经存在,正在使其失效...", tableName.getNameAsString());
			admin.disableTable(tableName);
			LOGGER.info("表【{}】已经失效", tableName.getNameAsString());
			LOGGER.info("表【{}】正在删除...", tableName.getNameAsString());
			admin.deleteTable(tableName);
			LOGGER.info("表【{}】已经删除", tableName.getNameAsString());
			return true;
		} else {
			LOGGER.info("表【{}】不存在，无法删除", tableName.getNameAsString());
			return false;
		}
	}

	/**
	 * @author jingzz
	 * @throws Exception
	 */
	public static boolean createTable(Connection conn,String tName,String cfName) throws Exception {
		Admin admin = conn.getAdmin();
		TableName tableName = TableName.valueOf(tName);
		HTableDescriptor desc = new HTableDescriptor(tableName);
		desc.addFamily(new HColumnDescriptor(cfName));

		if (!admin.tableExists(tableName)) {
			LOGGER.info("表【{}】不存在，正在创建...", tableName.getNameAsString());
			admin.createTable(desc);
			LOGGER.info("表【{}】已经创建", tableName.getNameAsString());
			return true;
		}
		LOGGER.info("表【{}】已经存在", tableName.getNameAsString());
		return false;
	}

	/**
	 * @author jingzz
	 * @throws IOException
	 */
	public static void getClusterStatus(Connection conn) throws IOException {
		Admin admin = conn.getAdmin();
		String clusterStatus = admin.getClusterStatus().toString();
		System.out.println("集群环境：==============");
		System.out.println(clusterStatus);
		System.out.println("结束");
	}
}
