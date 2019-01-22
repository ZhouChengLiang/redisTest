package org.zcl.mytest.poiExcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 出货单Excel
 * @author zhoucl
 *
 */
public class GoodsOutExcelExport {

	/**
	 * 创建 workbook
	 * 
	 * @return
	 */
	public HSSFWorkbook getHSSFWorkbook() {
		return new HSSFWorkbook();
	}

	/**
	 * 创建 sheet
	 * 
	 * @param hssfWorkbook
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook, String sheetName) {
		HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);
		return hssfSheet;
	}

	/**
	 * 写入额外行
	 * 
	 * @param hssfWorkbook
	 * @param hssfSheet
	 * @param headInfoList
	 */
	public void writeExtralColumn(HSSFWorkbook workbook, HSSFSheet sheet, List<Integer> crossColumns,
			List<ExtralContentVO> extralContents) {
		if (CollectionUtils.isEmpty(extralContents)) {
			return;
		}
		for (int i = 0; i < extralContents.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(extralContents.get(i).getContent());
			cell.setCellStyle(getCenterStyle(workbook, extralContents.get(i).getCenter()));
			if (CollectionUtils.isEmpty(crossColumns)) {
				return;
			}
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, extralContents.size() + crossColumns.size() - 1);
			sheet.addMergedRegion(cra);
		}
	}

	/**
	 * 获取居中或者居中的样式
	 * 
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle getCenterStyle(HSSFWorkbook workbook, boolean center) {
		HSSFCellStyle cs = workbook.createCellStyle();
		Font fontStyle = workbook.createFont();
		fontStyle.setFontHeightInPoints((short) 14);
		cs.setFont(fontStyle);
		if (center) {
			cs.setAlignment(HorizontalAlignment.CENTER);
			cs.setVerticalAlignment(VerticalAlignment.CENTER);
			cs.setWrapText(true);
		} else {
			cs.setAlignment(HorizontalAlignment.LEFT);
		}
		cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(CellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(CellStyle.BORDER_THIN);//右边框
		return cs;
	}

	/**
	 * 获取居中的样式
	 * 
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle getCenterStyle(HSSFWorkbook workbook) {
		return getCenterStyle(workbook, true);
	}

	/**
	 * 写入表头信息
	 * 
	 * @param hssfWorkbook
	 * @param hssfSheet
	 * @param headInfoList
	 * @throws Exception
	 */
	public void writeHeader(HSSFWorkbook workbook, HSSFSheet sheet, List<String> headList, Integer extralRows)
			throws Exception {
		if (extralRows < 0) {
			throw new Exception("起始行数不能小于 0");
		}
		HSSFRow row = sheet.createRow(extralRows);
		for (int i = 0; i < headList.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headList.get(i));
			cell.setCellStyle(getCenterStyle(workbook));
		}
	}

	/**
	 * 绘制Excel 主体内容部分
	 * 
	 * @param hssfSheet
	 */
	public void writeContent(HSSFWorkbook workbook, HSSFSheet sheet, List<MyNetMachineVO> list, List<String> headList) {
		Integer nowRowNum = sheet.getLastRowNum() + 1;
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + nowRowNum);
			for (int j = 0; j < headList.size(); j++) {
				HSSFCell cell = row.createCell(j);
				if (j == 0) {
					cell.setCellValue(list.get(i).getNetName());
				} else if (j == 1) {
					cell.setCellValue(list.get(i).getMachineName());
				} else {
					cell.setCellValue(list.get(i).getPlanNum().get(j - 2));
				}
				cell.setCellStyle(getCenterStyle(workbook));
			}
		}
	}

	/**
	 * 绘制最后一行实际带货数
	 * 
	 * @param hssfWorkbook
	 * @param hssfSheet
	 */
	public void writeFooter(HSSFWorkbook workbook, HSSFSheet sheet, List<String> headList,
			List<Integer> realBringOutNums, String footerContent) {
		Integer nowRowNum = sheet.getLastRowNum();
		HSSFRow row = sheet.createRow(nowRowNum + 1);
		for (int j = 0; j < headList.size(); j++) {
			HSSFCell cell = row.createCell(j);
			if (j <= 1) {
				cell.setCellValue(footerContent);
			} else {
				cell.setCellValue(realBringOutNums.get(j - 2));
			}
			cell.setCellStyle(getCenterStyle(workbook));
		}
		CellRangeAddress cra = new CellRangeAddress(nowRowNum + 1, nowRowNum + 1, 0, 1);
		sheet.addMergedRegion(cra);
	}

	public void writeAllColumnWidth(HSSFSheet sheet, List<String> headList) {
		for (int i = 0; i < headList.size(); i++) {
			sheet.setColumnWidth(i, 20 * 256);
		}
	}

	/**
	 * 根据坐标合并单元格 设置单元格样式 居中
	 * 
	 * @param hssfWorkbook
	 * @param hssfSheet
	 * @param spanRows     记录的跨行数
	 * @param endColumn    需要参与跨行的最后一列
	 */
	public void mergeRows(HSSFWorkbook workbook, HSSFSheet sheet, List<Integer> spanRows, int endColumn, int minRow) {
		Map<Integer, Integer> positions = transPositionMap(spanRows, minRow);
		for (int i = 0; i < endColumn; i++) {
			for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
				CellRangeAddress cra = new CellRangeAddress(entry.getKey(), entry.getValue(), i, i);
				sheet.addMergedRegion(cra);
			}
		}
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = 0; j < endColumn; j++) {
				HSSFCell cell = row.getCell(j);
				cell.setCellStyle(getCenterStyle(workbook));
			}
		}
	}

	/**
	 * 跨行计算坐标点
	 *
	 * @param list   跨行的数量集合
	 * @param minRow 从哪一行开始跨行
	 */
	private Map<Integer, Integer> transPositionMap(List<Integer> list, Integer minRow) {
		if (CollectionUtils.isNotEmpty(list)) {
			Map<Integer, Integer> linkedMap = new LinkedHashMap<>();
			List<Integer> xPositionList = new ArrayList<>();
			xPositionList.add(minRow);
			for (Integer pos : list) {
				if (pos == 1 || pos == 0) {
					pos = 1;
					Integer xPosition = Collections.max(xPositionList);
					xPositionList.add(xPosition + pos);
					continue;
				}
				Integer xPosition = Collections.max(xPositionList);
				linkedMap.put(xPosition, xPosition + pos - 1);
				xPositionList.add(xPosition + pos);
			}
			return linkedMap;
		}
		return Collections.emptyMap();
	}
}
