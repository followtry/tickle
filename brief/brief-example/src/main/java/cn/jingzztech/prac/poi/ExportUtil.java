package cn.jingzztech.prac.poi;

import java.awt.Font;

/**
 * 
 */

import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * 导出测试
 * @author jingzz
 * @time 2016年2月1日 上午10:07:32
 * @func 
 * @name ExportTest
 */
public class ExportUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExportUtil.class);

	/**
	 * 导出账单查询信息
	 * @param title
	 * @param dataset
	 * @param out
	 * @param pattern
	 */
	public static void exportBillInfosExcel(String title,List<Object> dataset, OutputStream out, String pattern) {
		String[] headers = {"账单月","etpId","企业名称","子账号id","子账号名称","总消费(元)","落地电话消费(元)","网络电话消费(元)"};
		exportBillInfosExcel(title, headers, dataset, out, pattern);
	}
	
	public static void exportBillInfosExcel(String title, String[] headers, List<Object> dataset, OutputStream out, String pattern) {
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个表
		HSSFSheet sheet = workbook.createSheet(title);
		//设置表格的默认列高
		sheet.setDefaultColumnWidth(20);
		
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString cellText = new HSSFRichTextString(headers[i]);
			cell.setCellValue(cellText);
		}
		
		Iterator<Object> iter = dataset.iterator();
		int dataRowIndex = 1; 
		while (iter.hasNext()) {
//			BillQueryVO billQueryVO = (BillQueryVO) iter.next();
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			CellNumber column = new CellNumber(0);
//			addMonthCell(billQueryVO, dataRow, column);
//			addTextCell(billQueryVO.getEtpId(), dataRow, column);
//			addTextCell(billQueryVO.getEtpName(), dataRow, column);
//			addTextCell(billQueryVO.getSubAccountIdentify(), dataRow, column);
//			addTextCell(billQueryVO.getSubAccountName(), dataRow, column);
//			addCurrencyCell(billQueryVO.getTotalConsumedByMonth(), dataRow, column);
//			addCurrencyCell(billQueryVO.getPhoConsumedByMonth(), dataRow, column);
//			addCurrencyCell(billQueryVO.getNetConsumedByMonth(), dataRow, column);
			dataRowIndex++;
		}
		if (!CollectionUtils.isEmpty(dataset)) {
			try {
				workbook.write(out);
				LOG.info("导出成功");
			} catch (IOException e) {
				LOG.error("导出失败",e.toString());
			}finally {
				try {
					workbook.close();
					out.close();
					LOG.info("工作簿已经关闭");
				} catch (IOException e) {
					LOG.error("关闭工作簿失败",e.toString());
				}
			}
		}
	}

	private static void addMonthCell(Object billQueryVO, HSSFRow dataRow, CellNumber column) {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		//设置一下billMonth的值
//		billQueryVO.setBillMonth(billQueryVO.getBillMonthString());
//		HSSFCell monthCell = dataRow.createCell(column.getNum());
//		monthCell.setCellType(Cell.CELL_TYPE_STRING);
//		monthCell.setCellValue(df.format(TimeUtils.getDateOnlyYMD(billQueryVO.getBillMonth())));
		column.add();;
	}

	private static void addTextCell(String value, HSSFRow dataRow, CellNumber column) {
		HSSFCell cell = dataRow.createCell(column.getNum());
		HSSFCellStyle cs = cell.getCellStyle();
		cs.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
		cell.setCellValue(value);
		column.add();
	}

	private static void addCurrencyCell( Double value, HSSFRow dataRow, CellNumber column) {
		NumberFormat nf = getCurrencyFormat();
		HSSFCell cell = dataRow.createCell(column.getNum());
		cell.setCellType(Cell.CELL_TYPE_STRING);
		HSSFCellStyle cs = cell.getCellStyle();
		cs.setAlignment(CellStyle.ALIGN_RIGHT);
		cell.setCellValue(nf.format(value));
		column.add();
	}

	private static NumberFormat getCurrencyFormat() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setMinimumIntegerDigits(1);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		return nf;
	}
	

}
	class CellNumber{
		int num = 0;
		public CellNumber(int i){
			num = i;
		}
		public void add(){
			num++;
		}
		public void minu(){
			num--;
		}
		
		public int getNum(){
			return num;
		}
	}
