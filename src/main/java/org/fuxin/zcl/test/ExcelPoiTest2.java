package org.fuxin.zcl.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

public class ExcelPoiTest2 {  
  
    public static void main(String[] args) {  
          
        HSSFWorkbook workbook = new HSSFWorkbook();  // 创建一个excel  
        // excel生成过程: excel-->sheet-->row-->cell  
        HSSFSheet sheet = workbook.createSheet("test"); // 为excel创建一个名为test的sheet页
        HSSFRow row = sheet.createRow(0);
        String[] strArr = {"入库时间","产品名","商业公司","批号","单价","数量","金额","有效期","结算时间","实际结算数量","实际结算单价","实际结算金额","票号","税金","备注"};
        for(int i = 0;i<strArr.length;i++){
        	HSSFCell cell = row.createCell(i);
        	cell.setCellValue(strArr[i]);
        }
		
        for(int i= 1;i<strArr.length;i++){
        	row = sheet.createRow(i);
        	for(int j = 0;j<strArr.length;j++){
        		HSSFCell cell = row.createCell(j); 
        		cell.setCellValue("单元格"+i);
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
        for(int i = 1 ;i < 8;i++){
	           for(int j = 0;j<8 ;j++){
	        	   CellRangeAddress cra =new CellRangeAddress(2*(i-1)+1, 2*i, j, j);// 起始行, 终止行, 起始列, 终止列  
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
    public void test0(){
    	//String[] strArr = {"入库时间","产品名","商业公司","批号","单价","数量","金额","有效期","结算时间","实际结算数量","实际结算单价","实际结算金额","票号","税金","备注"};
    	AgentSettle agentSettle_0 = new AgentSettle(1, "p00001", new Date(),12.98d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t001",new Date(),"t000");
    	AgentSettle agentSettle_1 = new AgentSettle(1, "p00001", new Date(),12.98d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t002",new Date(),"t001");
    	AgentSettle agentSettle_2 = new AgentSettle(2, "p00002", new Date(),12.8d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t003",new Date(),"t002");
    	AgentSettle agentSettle_3 = new AgentSettle(1, "p00001", new Date(),12.98d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t004",new Date(),"t003");
    	AgentSettle agentSettle_4 = new AgentSettle(2, "p00002", new Date(),12.98d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t005",new Date(),"t004");
    	AgentSettle agentSettle_5 = new AgentSettle(3, "p00003", new Date(),12.98d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t005",new Date(),"t005");
    	AgentSettle agentSettle_6 = new AgentSettle(3, "p00003", new Date(),12.98d, 100,
    			1298d, new Date(), "AA药", "AA商业公司", 12,12d, 144d, 122d, "t005",new Date(),"t006");
    	List<AgentSettle> list = new ArrayList<>();
    	list.add(agentSettle_0);
    	list.add(agentSettle_1);
    	list.add(agentSettle_2);
    	list.add(agentSettle_3);
    	list.add(agentSettle_4);
    	list.add(agentSettle_5);
    	list.add(agentSettle_6);
    	Map<Integer,List<AgentSettle>> map = list.stream().collect(Collectors.groupingBy(AgentSettle::getStockId,LinkedHashMap::new,Collectors.toList()));
    	List<AgentSettle> result = new ArrayList<>();
    	for(Map.Entry<Integer, List<AgentSettle>> entry:map.entrySet()){
    		Integer count = entry.getValue().size();
    		for(AgentSettle ageSettle: entry.getValue()){
    			ageSettle.setSpanRows(count);
    			result.add(ageSettle);
    		}
    	}
        
        HSSFWorkbook workbook = new HSSFWorkbook();  // 创建一个excel  
        // excel生成过程: excel-->sheet-->row-->cell  
        HSSFSheet sheet = workbook.createSheet("test"); // 为excel创建一个名为test的sheet页
        HSSFRow row = sheet.createRow(0);
        String[] strArr = {"入库时间","产品名","商业公司","批号","单价","数量","金额","有效期","结算时间","实际结算数量","实际结算单价","实际结算金额","票号","税金","备注"};
        for(int i = 0;i<strArr.length;i++){
        	HSSFCell cell = row.createCell(i);
        	cell.setCellValue(strArr[i]);
        }
		
        for(int i = 1;i < strArr.length ;i++){
        	row = sheet.createRow(i);
        	for(int j = 0;j<strArr.length;j++){
        		HSSFCell cell = row.createCell(j); 
        		cell.setCellValue("单元格"+i);
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
        List<Integer> spanRows = Arrays.asList(2,4,5);// 0,2
        // 8行表示 合并列的到哪几列为止
        /*for(int i = 1 ;i < 8;i++){
	           for(int j = 0;j<8 ;j++){
        		   CellRangeAddress cra =new CellRangeAddress(2*(i-1)+1, 2*i, j, j);// 起始行, 终止行, 起始列, 终止列  
		           sheet.addMergedRegion(cra);  
		           RegionUtil.setBorderBottom(1, cra, sheet); // 下边框  
		           RegionUtil.setBorderLeft(1, cra, sheet); // 左边框  
		           RegionUtil.setBorderRight(1, cra, sheet); // 有边框  
		           RegionUtil.setBorderTop(1, cra, sheet); // 上边框  
	           }
	     }*/
        for(int j = 0;j<8 ;j++){//怎么由2,4,5  ->>> 1,2,3,6,7,11
        	CellRangeAddress cra =new CellRangeAddress(1, 2, j, j);// 起始行, 终止行, 起始列, 终止列  
            sheet.addMergedRegion(cra);  
            RegionUtil.setBorderBottom(1, cra, sheet); // 下边框  
            RegionUtil.setBorderLeft(1, cra, sheet); // 左边框  
            RegionUtil.setBorderRight(1, cra, sheet); // 有边框  
            RegionUtil.setBorderTop(1, cra, sheet); // 上边框  
            
            cra =new CellRangeAddress(3, 6, j, j);// 起始行, 终止行, 起始列, 终止列  
            sheet.addMergedRegion(cra);  
            RegionUtil.setBorderBottom(1, cra, sheet); // 下边框  
            RegionUtil.setBorderLeft(1, cra, sheet); // 左边框  
            RegionUtil.setBorderRight(1, cra, sheet); // 有边框  
            RegionUtil.setBorderTop(1, cra, sheet); // 上边框 
            
            cra =new CellRangeAddress(7, 11, j, j);// 起始行, 终止行, 起始列, 终止列  
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
    public void test1(){
    	//2,4,5  ->>> 1,2,3,6,7,11
	    	//1,2
	    	//3,6
	    	//7,11
    	/*List<Integer> list = Arrays.asList(1,2,3,4,5,6);
    	Map<Integer,Integer> linkedMap = new LinkedHashMap<>();
    	List<Integer> xPositionList = new ArrayList<>();
    	xPositionList.add(1);
    	for(Integer id:list){
    		Integer xPosition = Collections.max(xPositionList);
    		linkedMap.put(xPosition, xPosition+id-1);
    		xPositionList.add(xPosition+id);
    	}
    	System.out.println(linkedMap);*/
    	List<Integer> list = Arrays.asList(1,2,3,4,5,6);
    	Map<Integer,Integer> positions = transPositionMap(list, 1);
    	System.out.println(positions);
    }
    
    /**
     * 根据跨行数来转化 CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) 的 firstRow 与 lastRow
     * @param list   每条记录需要的跨行数
     * @param minRow 从第几行开始跨行
     * @return
     */
    private Map<Integer,Integer> transPositionMap(List<Integer> list,Integer minRow){
    	if(CollectionUtils.isNotEmpty(list)){
    		Map<Integer,Integer> linkedMap = new LinkedHashMap<>();
        	List<Integer> xPositionList = new ArrayList<>();
        	xPositionList.add(minRow);
        	for(Integer pos:list){
        		if(pos==1){
        			Integer xPosition = Collections.max(xPositionList);
        			xPositionList.add(xPosition+pos);
        			continue;
        		}
        		Integer xPosition = Collections.max(xPositionList);
        		linkedMap.put(xPosition, xPosition+pos-1);
        		xPositionList.add(xPosition+pos);
        	}
        	return linkedMap;
    	}
		return Collections.emptyMap();
    }
    
    @Test
    public void test2(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("test");
        HSSFRow row = sheet.createRow(0);
        String[] strArr = {"入库时间","产品名","商业公司","批号","单价","数量","金额","有效期","结算时间","实际结算数量","实际结算单价","实际结算金额","票号","税金","备注"};
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        Font fontStyle = workbook.createFont();
//        fontStyle.setBold(true);
//        fontStyle.setFontName("黑体"); 
        fontStyle.setFontHeightInPoints((short) 11);  
        cellStyle.setFont(fontStyle);  
        cellStyle.setAlignment(HorizontalAlignment.CENTER); 
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        for(int i = 0;i<strArr.length;i++){
        	HSSFCell cell = row.createCell(i);
        	cell.setCellValue(strArr[i]);
        	cell.setCellStyle(cellStyle);
        }
        for(int i = 1;i < 16 ;i++){
        	row = sheet.createRow(i);
        	for(int j = 0;j<strArr.length;j++){
        		HSSFCell cell = row.createCell(j); 
        		cell.setCellValue("单元格"+i);
        		cell.setCellStyle(cellStyle);
        	}
        }
        List<Integer> spanRows = Arrays.asList(1,2,3,4,3,2,1);
        Map<Integer,Integer> positions = transPositionMap(spanRows, 1);
        System.out.println("positions>>>>>>>>>>>>>>>>"+positions);
        for(int j = 0;j<8 ;j++){
        	for(Map.Entry<Integer, Integer> entry:positions.entrySet()){
        		CellRangeAddress cra =new CellRangeAddress(entry.getKey(),entry.getValue(), j, j);// 起始行, 终止行, 起始列, 终止列  
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
}
