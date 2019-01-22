package org.zcl.mytest.poiExcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class ExcelPoiTest2 {

	public static void main(String[] args) {

		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel
		// excel生成过程: excel-->sheet-->row-->cell
		HSSFSheet sheet = workbook.createSheet("test"); // 为excel创建一个名为test的sheet页
		HSSFRow row = sheet.createRow(0);
		String[] strArr = { "入库时间", "产品名", "商业公司", "批号", "单价", "数量", "金额", "有效期", "结算时间", "实际结算数量", "实际结算单价", "实际结算金额",
				"票号", "税金", "备注" };
		for (int i = 0; i < strArr.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(strArr[i]);
		}

		for (int i = 1; i < strArr.length; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < strArr.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue("单元格" + i);
			}
		}
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 单元格样式
		Font fontStyle = workbook.createFont(); // 字体样式
		fontStyle.setBold(true); // 加粗
		fontStyle.setFontName("黑体"); // 字体
		fontStyle.setFontHeightInPoints((short) 11); // 大小
		// 将字体样式添加到单元格样式中
		cellStyle.setFont(fontStyle);
		// 边框，居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		// 8行表示 合并列的到哪几列为止
		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				CellRangeAddress cra = new CellRangeAddress(2 * (i - 1) + 1, 2 * i, j, j);// 起始行, 终止行, 起始列, 终止列
				sheet.addMergedRegion(cra);
				RegionUtil.setBorderBottom(1, cra, sheet); // 下边框
				RegionUtil.setBorderLeft(1, cra, sheet); // 左边框
				RegionUtil.setBorderRight(1, cra, sheet); // 有边框
				RegionUtil.setBorderTop(1, cra, sheet); // 上边框
			}
		}

		// 输出到本地
		String excelName = "D:/myExcel.xls";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}
	}

	@Test
	public void test0() {
		// String[] strArr =
		// {"入库时间","产品名","商业公司","批号","单价","数量","金额","有效期","结算时间","实际结算数量","实际结算单价","实际结算金额","票号","税金","备注"};
		AgentSettle agentSettle_0 = new AgentSettle(1, "p00001", new Date(), 12.98d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t001", new Date(), "t000");
		AgentSettle agentSettle_1 = new AgentSettle(1, "p00001", new Date(), 12.98d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t002", new Date(), "t001");
		AgentSettle agentSettle_2 = new AgentSettle(2, "p00002", new Date(), 12.8d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t003", new Date(), "t002");
		AgentSettle agentSettle_3 = new AgentSettle(1, "p00001", new Date(), 12.98d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t004", new Date(), "t003");
		AgentSettle agentSettle_4 = new AgentSettle(2, "p00002", new Date(), 12.98d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t005", new Date(), "t004");
		AgentSettle agentSettle_5 = new AgentSettle(3, "p00003", new Date(), 12.98d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t005", new Date(), "t005");
		AgentSettle agentSettle_6 = new AgentSettle(3, "p00003", new Date(), 12.98d, 100, 1298d, new Date(), "AA药",
				"AA商业公司", 12, 12d, 144d, 122d, "t005", new Date(), "t006");
		List<AgentSettle> list = new ArrayList<>();
		list.add(agentSettle_0);
		list.add(agentSettle_1);
		list.add(agentSettle_2);
		list.add(agentSettle_3);
		list.add(agentSettle_4);
		list.add(agentSettle_5);
		list.add(agentSettle_6);
		/*
		 * Map<Integer,List<AgentSettle>> map =
		 * list.stream().collect(Collectors.groupingBy(AgentSettle::getStockId,
		 * LinkedHashMap::new,Collectors.toList())); List<AgentSettle> result = new
		 * ArrayList<>(); for(Map.Entry<Integer, List<AgentSettle>>
		 * entry:map.entrySet()){ Integer count = entry.getValue().size();
		 * for(AgentSettle ageSettle: entry.getValue()){ ageSettle.setSpanRows(count);
		 * result.add(ageSettle); } }
		 */

		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel
		// excel生成过程: excel-->sheet-->row-->cell
		HSSFSheet sheet = workbook.createSheet("test"); // 为excel创建一个名为test的sheet页
		HSSFRow row = sheet.createRow(0);
		String[] strArr = { "入库时间", "产品名", "商业公司", "批号", "单价", "数量", "金额", "有效期", "结算时间", "实际结算数量", "实际结算单价", "实际结算金额",
				"票号", "税金", "备注" };
		for (int i = 0; i < strArr.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(strArr[i]);
		}

		for (int i = 1; i < strArr.length; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < strArr.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue("单元格" + i);
			}
		}
		HSSFCellStyle cellStyle = workbook.createCellStyle(); // 单元格样式
		Font fontStyle = workbook.createFont(); // 字体样式
		fontStyle.setBold(true); // 加粗
		fontStyle.setFontName("黑体"); // 字体
		fontStyle.setFontHeightInPoints((short) 11); // 大小
		// 将字体样式添加到单元格样式中
		cellStyle.setFont(fontStyle);
		// 边框，居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		List<Integer> spanRows = Arrays.asList(2, 4, 5);// 0,2
		for (int j = 0; j < 8; j++) {// 怎么由2,4,5 ->>> 1,2,3,6,7,11
			CellRangeAddress cra = new CellRangeAddress(1, 2, j, j);// 起始行, 终止行, 起始列, 终止列
			sheet.addMergedRegion(cra);
			RegionUtil.setBorderBottom(1, cra, sheet); // 下边框
			RegionUtil.setBorderLeft(1, cra, sheet); // 左边框
			RegionUtil.setBorderRight(1, cra, sheet); // 有边框
			RegionUtil.setBorderTop(1, cra, sheet); // 上边框

			cra = new CellRangeAddress(3, 6, j, j);// 起始行, 终止行, 起始列, 终止列
			sheet.addMergedRegion(cra);
			RegionUtil.setBorderBottom(1, cra, sheet); // 下边框
			RegionUtil.setBorderLeft(1, cra, sheet); // 左边框
			RegionUtil.setBorderRight(1, cra, sheet); // 有边框
			RegionUtil.setBorderTop(1, cra, sheet); // 上边框

			cra = new CellRangeAddress(7, 11, j, j);// 起始行, 终止行, 起始列, 终止列
			sheet.addMergedRegion(cra);
			RegionUtil.setBorderBottom(1, cra, sheet); // 下边框
			RegionUtil.setBorderLeft(1, cra, sheet); // 左边框
			RegionUtil.setBorderRight(1, cra, sheet); // 有边框
			RegionUtil.setBorderTop(1, cra, sheet); // 上边框
		}

		// 输出到本地
		String excelName = "D:/myExcel.xls";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}

	}

	@Test
	public void test1() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
		Map<Integer, Integer> positions = transPositionMap(list, 1);
		System.out.println(positions);
	}

	/**
	 * 根据跨行数来转化 CellRangeAddress(int firstRow, int lastRow, int firstCol, int
	 * lastCol) 的 firstRow 与 lastRow
	 * 
	 * @param list   每条记录需要的跨行数
	 * @param minRow 从第几行开始跨行
	 * @return
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

	@Test
	public void test2() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("test");
		HSSFRow row = sheet.createRow(0);
		String[] strArr = { "入库时间", "产品名", "商业公司", "批号", "单价", "数量", "金额", "有效期", "结算时间", "实际结算数量", "实际结算单价", "实际结算金额",
				"票号", "税金", "备注" };
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		Font fontStyle = workbook.createFont();
//        fontStyle.setBold(true);
//        fontStyle.setFontName("黑体"); 
		fontStyle.setFontHeightInPoints((short) 11);
		cellStyle.setFont(fontStyle);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		for (int i = 0; i < strArr.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(strArr[i]);
			cell.setCellStyle(cellStyle);
		}
		for (int i = 1; i < 20; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < strArr.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue("单元格" + i);
				cell.setCellStyle(cellStyle);
			}
		}
		List<Integer> spanRows = Arrays.asList(1, 3, 4, 4, 4, 3, 1);
		Map<Integer, Integer> positions = transPositionMap(spanRows, 1);
		System.out.println("positions>>>>>>>>>>>>>>>>" + positions);
		for (int j = 0; j < 8; j++) {
			for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
				CellRangeAddress cra = new CellRangeAddress(entry.getKey(), entry.getValue(), j, j);// 起始行, 终止行, 起始列,
																									// 终止列
				sheet.addMergedRegion(cra);
			}
		}
		String excelName = "D:/myExcel.xls";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}

	}

	@Test
	public void test3() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("test");
		HSSFRow row = sheet.createRow(0);
		String[] strArr = { "入库时间", "产品名", "商业公司", "批号", "单价", "数量", "金额", "有效期", "结算时间", "实际结算数量", "实际结算单价", "实际结算金额",
				"票号", "税金", "备注" };
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		Font fontStyle = workbook.createFont();
		fontStyle.setFontHeightInPoints((short) 11);
		cellStyle.setFont(fontStyle);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		for (int i = 0; i < strArr.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(strArr[i]);
			cell.setCellStyle(cellStyle);
		}
		for (int i = 1; i < 20; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < strArr.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue("单元格" + i);
				cell.setCellStyle(cellStyle);
			}
		}
		// spanRows 表示 每条记录可能的跨行数
		List<Integer> spanRows = Arrays.asList(1, 3, 4, 4, 4, 3, 1);
		Map<Integer, Integer> positions = transPositionMap(spanRows, 1);
		for (int j = 0; j < 8; j++) {
			for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
				CellRangeAddress cra = new CellRangeAddress(entry.getKey(), entry.getValue(), j, j);// 起始行, 终止行, 起始列,
																									// 终止列
				sheet.addMergedRegion(cra);
			}
		}
		String excelName = "/Users/zhoucl/Desktop/myExcel.xls";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}
	}

	/**
	 * 新版的出货单的Excel
	 */
	@Test
	public void test4() {
		// 准备物品详情数据
		List<MyPlanVO> myPlanList = new ArrayList<>();

		MyPlanVO v1 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "12121121", "浙江工业大学(留下校区)12121121", 67, 2, "41@12121121");
		MyPlanVO v2 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "12121121", "浙江工业大学(留下校区)12121121", 70, 1, "41@12121121");
		MyPlanVO v3 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 67, 3, "41@NFZF0001");
		MyPlanVO v4 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 68, 4, "41@NFZF0001");
		MyPlanVO v5 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 69, 5, "41@NFZF0001");
		
		MyPlanVO v6 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 65, 1, "742@12312321");
		MyPlanVO v7 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 66, 2, "742@12312321");
		MyPlanVO v8 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 84, 4, "742@12312321");
		MyPlanVO v9 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 85, 3, "742@12312321");
		
		MyPlanVO v101 = new MyPlanVO(1, 745, "理想城", "12312320", "理想城12312320", 65, 6, "745@12312320");
		MyPlanVO v111 = new MyPlanVO(1, 745, "理想城", "12312324", "理想城12312324", 66, 5, "745@12312324");
		MyPlanVO v121 = new MyPlanVO(1, 745, "理想城", "12312322", "理想城12312322", 84, 4, "745@12312322");
		MyPlanVO v131 = new MyPlanVO(1, 745, "理想城", "12312323", "理想城12312323", 85, 3, "745@12312323");
		
		MyPlanVO v1011 = new MyPlanVO(1, 795, "欢乐谷", "12312330", "欢乐谷12312330", 71, 6, "795@12312330");
		MyPlanVO v1111 = new MyPlanVO(1, 795, "欢乐谷", "12312332", "欢乐谷12312332", 72, 5, "795@12312332");
		MyPlanVO v1211 = new MyPlanVO(1, 795, "欢乐谷", "12312333", "欢乐谷12312333", 73, 4, "795@12312333");
		MyPlanVO v1311 = new MyPlanVO(1, 795, "欢乐谷", "12312333", "欢乐谷12312323", 74, 3, "795@12312333");
		
		myPlanList.add(v1);
		myPlanList.add(v2);
		myPlanList.add(v3);
		myPlanList.add(v4);
		myPlanList.add(v5);
		myPlanList.add(v6);
		myPlanList.add(v7);
		myPlanList.add(v8);
		myPlanList.add(v9);
		
		myPlanList.add(v101);
		myPlanList.add(v111);
		myPlanList.add(v121);
		myPlanList.add(v131);
		
		myPlanList.add(v1011);
		myPlanList.add(v1111);
		myPlanList.add(v1211);
		myPlanList.add(v1311);

		// 准备物品品项对应
		List<SkuVO> skuList = new ArrayList<>();
		for(int i = 65;i<101;i++) {
			SkuVO v10 = new SkuVO(i, i+"#Shopping", i+" * 550 ml");
			skuList.add(v10);
		}
		// 准备实际的品项对应带货数
		List<MyRealVO> myRealVOs = new ArrayList<>();
		MyRealVO v20 = new MyRealVO(1, 65, 65);
		MyRealVO v21 = new MyRealVO(1, 66, 66);
		MyRealVO v22 = new MyRealVO(1, 67, 67);
		MyRealVO v23 = new MyRealVO(1, 68, 68);
		MyRealVO v24 = new MyRealVO(1, 69, 69);
		MyRealVO v25 = new MyRealVO(1, 70, 70);
		MyRealVO v26 = new MyRealVO(1, 99, 99);
		MyRealVO v27 = new MyRealVO(1, 100, 100);
		myRealVOs.add(v20);
		myRealVOs.add(v21);
		myRealVOs.add(v22);
		myRealVOs.add(v23);
		myRealVOs.add(v24);
		myRealVOs.add(v25);
		myRealVOs.add(v26);
		myRealVOs.add(v27);

		// 映射好 skuId 与 SkuVO 对应关系
		Map<Integer, SkuVO> skuMap = skuList.stream().collect(Collectors.toMap(x -> x.getSkuId(), y -> y));

		// 机器ID 对应机器名称
		Map<String, String> machineNameMap = myPlanList.stream()
				.collect(Collectors.toMap(x -> x.getMachineId(), y -> y.getMachineName(), (key1, key2) -> key2));

		// 网点ID 对应网点名称
		Map<Integer, String> netNameMap = myPlanList.stream()
				.collect(Collectors.toMap(x -> x.getNetId(), y -> y.getNetName(), (key1, key2) -> key2));

		// 为表头数据准备  应该把出货单 与 实际带货的 skuId 合并
//		List<Integer> skuIds = myPlanList.stream().map(x -> x.getSkuId()).distinct().collect(Collectors.toList());
		Set<Integer> part1 = myPlanList.stream().map(x -> x.getSkuId()).collect(Collectors.toSet());
		Set<Integer> part2 = myRealVOs.stream().map(x -> x.getSkuId()).collect(Collectors.toSet());
		part1.addAll(part2);
		List<Integer> skuIds = new ArrayList<>(part1);
		
		
		skuIds.sort(Comparator.comparing(Integer::intValue));

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("test");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		Font fontStyle = workbook.createFont();
		fontStyle.setFontHeightInPoints((short) 14);
		cellStyle.setFont(fontStyle);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setWrapText(true);
		// 跨列数 = 2 + skuIds.size();
		// 第一行出货单信息
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("出货单");
		cell.setCellStyle(cellStyle);

		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 2 + skuIds.size() - 1);// 起始行, 终止行, 起始列, 终止列
		sheet.addMergedRegion(cra);

		// 第二行 操作员与创建时间
		row = sheet.createRow(1);
		cell = row.createCell(0);
		HSSFCellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setAlignment(HorizontalAlignment.LEFT);
		cellStyle1.setFont(fontStyle);
		cell.setCellValue("补货员：张三      创建时间：2018-12-27 10:32:25");
		cell.setCellStyle(cellStyle1);

		cra = new CellRangeAddress(1, 1, 0, 2 + skuIds.size() - 1);// 起始行, 终止行, 起始列, 终止列
		sheet.addMergedRegion(cra);
		// 略过两行 直接 绘制主表头
		// 开始主表头处理--------------------------------------------------------------------

		List<String> headStr = new ArrayList<>();
		headStr.add("网点");
		headStr.add("机器");
		skuIds.forEach(obj -> {
			String str = "";
			if (!Objects.isNull(skuMap.get(obj))) {
				SkuVO vo = skuMap.get(obj);
				str = str.concat(vo.getSkuName()).concat("---").concat(vo.getSpecification());
			}
			headStr.add(str);
		});
		row = sheet.createRow(2);
		for (int i = 0; i < headStr.size(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(headStr.get(i));
			cell.setCellStyle(cellStyle);
		}
		// 表头处理完毕--------------------------------------------------------------------
		// 开始组装表体内容----------------------------------------------------------------
		Map<String, List<MyPlanVO>> groupByMap = myPlanList.stream()
				.collect(Collectors.groupingBy(MyPlanVO::getGroupByStr));
		groupByMap = sortMapByKey(groupByMap);
		groupByMap.forEach((k, v) -> {
			v.sort(Comparator.comparing(MyPlanVO::getSkuId));
		});

		List<MyNetMachineVO> lastVo = new ArrayList<>();
		groupByMap.forEach((k, v) -> {
			String[] strArr = k.split("@");
			Integer netId = Integer.valueOf(strArr[0]);
			String machineId = strArr[1];
			MyNetMachineVO vo = new MyNetMachineVO();
			vo.setNetName(netNameMap.get(netId));
			vo.setMachineName(machineNameMap.get(machineId));
			Map<Integer, Integer> inner = new LinkedHashMap<>();
			skuIds.forEach(obj -> {
				inner.put(obj, 0);
			});
			v.forEach(obj -> {
				if (inner.containsKey(obj.getSkuId())) {
					inner.put(obj.getSkuId(), obj.getPlanBringOutNum());
				}
			});
			vo.setPlanNum(new ArrayList<>(inner.values()));
			lastVo.add(vo);
		});
		System.out.println(JSON.toJSONString(lastVo));
		// 表体内容组装完毕-----------------------------------------------
		// 开始填充内容--------------------------------------------------
		for (int i = 0; i < lastVo.size(); i++) {
			row = sheet.createRow(i + 3);
			for (int j = 0; j < headStr.size(); j++) {
				cell = row.createCell(j);
				if (j == 0) {
					cell.setCellValue(lastVo.get(i).getNetName());
				} else if (j == 1) {
					cell.setCellValue(lastVo.get(i).getMachineName());
				} else {
					cell.setCellValue(lastVo.get(i).getPlanNum().get(j - 2));
				}
				cell.setCellStyle(cellStyle);
			}
		}
		// 填充内容完毕 ------------------------------------------------------
		// 准备 合并行  计算 合并行的坐标点
		Map<Object, Set<String>> mapSpanRows = myPlanList.stream().collect(Collectors.toMap(k->k.getNetId(), y->{
			Set<String> set = new HashSet<>();
			set.add(y.getMachineId());
			return set;
		}, (Set<String> s1,Set<String> s2)->{
			s1.addAll(s2);
			return s1;
		},LinkedHashMap::new));
//		List<Integer> spanRows = Arrays.asList(2, 1);// 这一步还是有点问题
		List<Integer> spanRows = new ArrayList<>();
		mapSpanRows.forEach((k,v)->{
			spanRows.add(v.size());
		});
		System.out.println("spanRows >>>>> "+spanRows);
		Map<Integer, Integer> positions = transPositionMap(spanRows, 3);
		for (int j = 0; j < 1; j++) {
			for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
				cra = new CellRangeAddress(entry.getKey(), entry.getValue(), j, j);// 起始行, 终止行, 起始列, 终止列
				sheet.addMergedRegion(cra);
			}
		}
		// 最后一行 实际带货数
		Integer nowRowNum = sheet.getLastRowNum();
		row = sheet.createRow(nowRowNum + 1);
		//
		Map<Integer, Integer> realInner = new LinkedHashMap<>();
		skuIds.forEach(obj -> {
			realInner.put(obj, 0);
		});
		myRealVOs.forEach(obj -> {
			if (realInner.containsKey(obj.getSkuId())) {
				realInner.put(obj.getSkuId(), obj.getRealBringOutNum());
			}
		});
		List<Integer> reals = new ArrayList<>(realInner.values());

		for (int j = 0; j < headStr.size(); j++) {
			cell = row.createCell(j);
			if (j == 0) {
				cell.setCellValue("实际带货数");
			} else if (j == 1) {
				cell.setCellValue("实际带货数");
			} else {
				cell.setCellValue(reals.get(j - 2));
			}
			cell.setCellStyle(cellStyle);
		}
		cra = new CellRangeAddress(nowRowNum + 1, nowRowNum + 1, 0, 1);// 起始行, 终止行, 起始列, 终止列
		sheet.addMergedRegion(cra);
//        sheet.autoSizeColumn((short)0);

		for (int i = 0; i < skuIds.size() + 2; i++) {
			sheet.setColumnWidth(i, 20 * 256);
		}

		String excelName = "/Users/zhoucl/Desktop/myExcel.xls";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}

	}
	
	/**
	 * 走走Util方法
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		// 准备物品详情数据
		List<MyPlanVO> myPlanList = new ArrayList<>();
		MyPlanVO v1 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "12121121", "浙江工业大学(留下校区)12121121", 67, 2, "41@12121121");
		MyPlanVO v2 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "12121121", "浙江工业大学(留下校区)12121121", 70, 1, "41@12121121");
		MyPlanVO v3 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 67, 3, "41@NFZF0001");
		MyPlanVO v4 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 68, 4, "41@NFZF0001");
		MyPlanVO v5 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 69, 5, "41@NFZF0001");
		
		MyPlanVO v6 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 65, 1, "742@12312321");
		MyPlanVO v7 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 66, 2, "742@12312321");
		MyPlanVO v8 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 84, 4, "742@12312321");
		MyPlanVO v9 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 85, 3, "742@12312321");
		
		MyPlanVO v101 = new MyPlanVO(1, 745, "理想城", "12312320", "理想城12312320", 65, 6, "745@12312320");
		MyPlanVO v111 = new MyPlanVO(1, 745, "理想城", "12312324", "理想城12312324", 66, 5, "745@12312324");
		MyPlanVO v121 = new MyPlanVO(1, 745, "理想城", "12312322", "理想城12312322", 84, 4, "745@12312322");
		MyPlanVO v131 = new MyPlanVO(1, 745, "理想城", "12312323", "理想城12312323", 85, 3, "745@12312323");
		
		MyPlanVO v1011 = new MyPlanVO(1, 795, "欢乐谷", "12312330", "欢乐谷12312330", 71, 6, "795@12312330");
		MyPlanVO v1111 = new MyPlanVO(1, 795, "欢乐谷", "12312332", "欢乐谷12312332", 72, 5, "795@12312332");
		MyPlanVO v1211 = new MyPlanVO(1, 795, "欢乐谷", "12312333", "欢乐谷12312333", 73, 4, "795@12312333");
		MyPlanVO v1311 = new MyPlanVO(1, 795, "欢乐谷", "12312333", "欢乐谷12312323", 74, 3, "795@12312333");
		
		myPlanList.add(v1);
		myPlanList.add(v2);
		myPlanList.add(v3);
		myPlanList.add(v4);
		myPlanList.add(v5);
		myPlanList.add(v6);
		myPlanList.add(v7);
		myPlanList.add(v8);
		myPlanList.add(v9);
		
		myPlanList.add(v101);
		myPlanList.add(v111);
		myPlanList.add(v121);
		myPlanList.add(v131);
		
		myPlanList.add(v1011);
		myPlanList.add(v1111);
		myPlanList.add(v1211);
		myPlanList.add(v1311);
		
		// 准备物品品项对应
		List<SkuVO> skuList = new ArrayList<>();
		for(int i = 65;i<101;i++) {
			SkuVO v10 = new SkuVO(i, i+"#Shopping", i+" * 550 ml");
			skuList.add(v10);
		}
		// 准备实际的品项对应带货数
		List<MyRealVO> myRealVOs = new ArrayList<>();
		MyRealVO v20 = new MyRealVO(1, 65, 65);
		MyRealVO v21 = new MyRealVO(1, 66, 66);
		MyRealVO v22 = new MyRealVO(1, 67, 67);
		MyRealVO v23 = new MyRealVO(1, 68, 68);
		MyRealVO v24 = new MyRealVO(1, 69, 69);
		MyRealVO v25 = new MyRealVO(1, 70, 70);
		MyRealVO v26 = new MyRealVO(1, 99, 99);
		MyRealVO v27 = new MyRealVO(1, 100, 100);
		myRealVOs.add(v20);
		myRealVOs.add(v21);
		myRealVOs.add(v22);
		myRealVOs.add(v23);
		myRealVOs.add(v24);
		myRealVOs.add(v25);
		myRealVOs.add(v26);
		myRealVOs.add(v27);
		
		// 映射好 skuId 与 SkuVO 对应关系
		Map<Integer, SkuVO> skuMap = skuList.stream().collect(Collectors.toMap(x -> x.getSkuId(), y -> y));
		
		// 机器ID 对应机器名称
		Map<String, String> machineNameMap = myPlanList.stream()
				.collect(Collectors.toMap(x -> x.getMachineId(), y -> y.getMachineName(), (key1, key2) -> key2));
		
		// 网点ID 对应网点名称
		Map<Integer, String> netNameMap = myPlanList.stream()
				.collect(Collectors.toMap(x -> x.getNetId(), y -> y.getNetName(), (key1, key2) -> key2));
		
		// 为表头数据准备  应该把出货单 与 实际带货的 skuId 合并
		Set<Integer> part1 = myPlanList.stream().map(x -> x.getSkuId()).collect(Collectors.toSet());
		Set<Integer> part2 = myRealVOs.stream().map(x -> x.getSkuId()).collect(Collectors.toSet());
		part1.addAll(part2);
		List<Integer> skuIds = new ArrayList<>(part1);
		skuIds.sort(Comparator.comparing(Integer::intValue));
		
		GoodsOutExcelExport execl = new GoodsOutExcelExport();
		HSSFWorkbook workbook = execl.getHSSFWorkbook();
		HSSFSheet sheet = execl.getHSSFSheet(workbook, "出货单");
		List<ExtralContentVO> extralContents  = new ArrayList<>();
		ExtralContentVO extralContentVO1 = new ExtralContentVO("出货单",true);
		ExtralContentVO extralContentVO2 = new ExtralContentVO("补货员：张三      创建时间：2018-12-27 11:11:11",false);
		extralContents.add(extralContentVO1);
		extralContents.add(extralContentVO2);
		execl.writeExtralColumn(workbook, sheet, skuIds, extralContents);
		
		List<String> headList = new ArrayList<>();
		headList.add("网点");
		headList.add("机器");
		skuIds.forEach(obj -> {
			String str = "";
			if (!Objects.isNull(skuMap.get(obj))) {
				SkuVO vo = skuMap.get(obj);
				str = str.concat(vo.getSkuName()).concat("---").concat(vo.getSpecification());
			}
			headList.add(str);
		});
		
		execl.writeHeader(workbook, sheet, headList, extralContents.size());
		
		Map<String, List<MyPlanVO>> groupByMap = myPlanList.stream()
				.collect(Collectors.groupingBy(MyPlanVO::getGroupByStr));
		groupByMap = sortMapByKey(groupByMap);
		groupByMap.forEach((k, v) -> {
			v.sort(Comparator.comparing(MyPlanVO::getSkuId));
		});
		List<MyNetMachineVO> lastVo = new ArrayList<>();
		groupByMap.forEach((k, v) -> {
			String[] strArr = k.split("@");
			Integer netId = Integer.valueOf(strArr[0]);
			String machineId = strArr[1];
			MyNetMachineVO vo = new MyNetMachineVO();
			vo.setNetName(netNameMap.get(netId));
			vo.setMachineName(machineNameMap.get(machineId));
			Map<Integer, Integer> inner = new LinkedHashMap<>();
			skuIds.forEach(obj -> {
				inner.put(obj, 0);
			});
			v.forEach(obj -> {
				if (inner.containsKey(obj.getSkuId())) {
					inner.put(obj.getSkuId(), obj.getPlanBringOutNum());
				}
			});
			vo.setPlanNum(new ArrayList<>(inner.values()));
			lastVo.add(vo);
		});
		
		execl.writeContent(workbook,sheet, lastVo, headList);
		Map<Object, Set<String>> mapSpanRows = myPlanList.stream().collect(Collectors.toMap(k->k.getNetId(), y->{
			Set<String> set = new HashSet<>();
			set.add(y.getMachineId());
			return set;
		}, (Set<String> s1,Set<String> s2)->{
			s1.addAll(s2);
			return s1;
		},LinkedHashMap::new));
		
		List<Integer> spanRows = new ArrayList<>();
		mapSpanRows.forEach((k,v)->{
			spanRows.add(v.size());
		});
		execl.mergeRows(workbook, sheet, spanRows, 1, 3);
		// 最后一行 实际带货数
		Map<Integer, Integer> realInner = new LinkedHashMap<>();
		skuIds.forEach(obj -> {
			realInner.put(obj, 0);
		});
		myRealVOs.forEach(obj -> {
			if (realInner.containsKey(obj.getSkuId())) {
				realInner.put(obj.getSkuId(), obj.getRealBringOutNum());
			}
		});
		List<Integer> reals = new ArrayList<>(realInner.values());
		execl.writeFooter(workbook, sheet, headList, reals, "实际带货数");
		execl.writeAllColumnWidth(sheet, headList);
		String excelName = "/Users/zhoucl/Desktop/myExcel.xls";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}
		
	}

	public static Map<String, List<MyPlanVO>> sortMapByKey(Map<String, List<MyPlanVO>> map) {
		if (map == null || map.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, List<MyPlanVO>> sortMap = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
		sortMap.putAll(map);
		return sortMap;
	}

	@Test
	public void test5() {
		// 准备物品详情数据
		List<MyPlanVO> myPlanList = new ArrayList<>();

		MyPlanVO v1 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "12121121", "浙江工业大学(留下校区)12121121", 67, 2, "41@12121121");
		MyPlanVO v2 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "12121121", "浙江工业大学(留下校区)12121121", 70, 1, "41@12121121");
		MyPlanVO v3 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 67, 3, "41@NFZF0001");
		MyPlanVO v4 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 68, 4, "41@NFZF0001");
		MyPlanVO v5 = new MyPlanVO(1, 41, "浙江工业大学(留下校区)", "NFZF0001", "浙江工业大学(留下校区)NFZF0001", 69, 5, "41@NFZF0001");
		MyPlanVO v6 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 65, 1, "742@12312321");
		MyPlanVO v7 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 66, 2, "742@12312321");
		MyPlanVO v8 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 84, 4, "742@12312321");
		MyPlanVO v9 = new MyPlanVO(1, 742, "翡翠城5", "12312321", "翡翠城512312321", 85, 3, "742@12312321");
		myPlanList.add(v1);
		myPlanList.add(v2);
		myPlanList.add(v3);
		myPlanList.add(v4);
		myPlanList.add(v5);
		myPlanList.add(v6);
		myPlanList.add(v7);
		myPlanList.add(v8);
		myPlanList.add(v9);
		
		Map<Object, Set<String>> map = myPlanList.stream().collect(Collectors.toMap(k->k.getNetId(), y->{
			Set<String> set = new HashSet<>();
			set.add(y.getMachineId());
			return set;
		}, (Set<String> s1,Set<String> s2)->{
			s1.addAll(s2);
			return s1;
		},LinkedHashMap::new));
		System.out.println(JSON.toJSONString(map));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Map<Integer, Long> resultMap = myPlanList.stream().collect(Collectors.groupingBy(MyPlanVO::getNetId, Collectors.counting()));
		System.out.println(resultMap);
	}
	
	@Test
	public void test7() {
		List<MyPlanVO> myPlanList = new ArrayList<>();
		myPlanList.sort(Comparator.comparing(MyPlanVO::getPlanBringOutNum).reversed());
	}
	
	@Test
	public void test8() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<5;i++) {
			
			sb.append("A");
			sb.append("B");
			sb.append("C");
			System.out.println("i >>>>> "+sb.toString());
			sb.setLength(0);
		}
	}
	
	public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }
	public <K extends Comparable<? super K>, V > Map<K, V> sortByKey(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByKey()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }




}
