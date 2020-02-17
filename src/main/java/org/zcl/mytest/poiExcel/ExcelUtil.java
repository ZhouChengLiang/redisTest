package org.zcl.mytest.poiExcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 生成Excel文件
 *
 * @author zongyl
 */
public class ExcelUtil {
	private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel 

    /**
     * 到处excel字节，不生成文件
     *
     * @param data
     * @return
     */
    public static byte[] createExcel(List<List<String>> data) throws IOException {
        try (Workbook wb = new HSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Sheet1");
            int lineNo = 0;
            for (List<String> line : data) {
                Row row = sheet.createRow(lineNo);
                int cellNo = 0;
                for (String s : line) {
                    Cell cell = row.createCell(cellNo);
                    cell.setCellValue(s);
                    cellNo++;
                }
                lineNo++;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 2);
            wb.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
    
    /** 
     * 导入，不生成文件，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public static List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception{  
        List<List<Object>> list = null;  
          
        //创建Excel工作薄  
        Workbook work = getWorkbook(in,fileName);  
        if(null == work){  
            throw new Exception("创建Excel工作薄为空！");  
        }  
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  
          
        list = new ArrayList<List<Object>>();  
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet == null) continue;
              
            //遍历当前sheet中的所有行。  sheet.getFirstRowNum()=0  sheet.getLastRowNum()=3
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {  
                row = sheet.getRow(j);  
                if(row==null) continue;   
                //遍历所有的列.   row.getFirstCellNum()=0  row.getLastCellNum()=1
                List<Object> li = new ArrayList<Object>();  
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                    cell = row.getCell(y);  
                    li.add(getCellValue(cell));  
                }  
                list.add(li);  
            }  
        }  
        work.close();  
        return list;  
    }  
      
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public  static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(excel2007U.equals(fileType)){  
//            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  
  
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public static Object getCellValue(Cell cell){  
        Object value = null;  
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
          
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC: 
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }  
    
    public static int isExcleVersion(String originalFilename){
    	if(StringUtils.isEmpty(originalFilename)){
    		return 0; 
    	}
        int i = 0;
        if(originalFilename.matches("^.+\\.(?i)(xls)$")){
        	i = 1; 
        }else if(originalFilename.matches("^.+\\.(?i)(xlsx)$")){
        	i = 2;
        }
        return i;
    }

}
