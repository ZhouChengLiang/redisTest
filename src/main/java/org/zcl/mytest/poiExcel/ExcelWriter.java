package org.zcl.mytest.poiExcel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class ExcelWriter {

	private HSSFWorkbook workbook;
	private String currentSheetName;
	private ExcelWriteContext context;
	private int sheetCount=0;
	private Font default_font=null;
	private CellStyle default_Text_Style=null;
	
	private List<SheetSize> sheetSizes=new ArrayList<>();
	
	public static final String DEFAULT_DATE_FORMAT="m/d/yy";
	public static final String DEFAULT_DATETIME_FORMAT="yy-m-d h:mm";
	public static final String DEFAULT_DOUBLE_FORMAT="#0.00";
	public static final String DEFAULT_LONG_FORMAT="#0";
	public static final String DEFAULT_CURRENCY_FORMAT="#,##0.00";
	
	public static final short HORIZONTAL_ALIGN_CENTER= CellStyle.ALIGN_CENTER;
	public static final short HORIZONTAL_ALIGN_LEFT= CellStyle.ALIGN_LEFT;
	public static final short HORIZONTAL_ALIGN_RIGHT= CellStyle.ALIGN_RIGHT;
	public static final short VERTICAL_ALIGN_TOP= CellStyle.VERTICAL_TOP;
	public static final short VERTICAL_ALIGN_BOTTOM= CellStyle.VERTICAL_BOTTOM;
	public static final short VERTICAL_ALIGN_CENTER= CellStyle.VERTICAL_CENTER;
	
//	private  static   short  XLS_ENCODING  =  HSSFWorkbook.ENCODING_UTF_16;
	
	/**
	 * FIXME
	 * @param name
	 */
	public void addWorkSheet(String name){
		if (emptyStringValue(name)) throw new IllegalArgumentException("工作表名称不能为空");
		if (getWorkBook().getSheet(name)!=null){
			throw new IllegalArgumentException("["+name+"]工作表已经存在");
		}else{
			HSSFSheet sheet = getWorkBook().createSheet(name);
			sheet.setDefaultColumnWidth(20);  
			sheet.setDefaultRowHeightInPoints(20);  
			currentSheetName=name;
			this.sheetCount++;
		}
	}
	
	public CellConstraints addCell(int row,int column,String value){
		HSSFCell cell=getCell(row,column);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(getWorkBook().getCreationHelper().createRichTextString(value));
		cell.setCellStyle(getDefaultCellStyle());
		setSheetSize(currentSheetName,row,column);
		return constructCellConstraints(row,column);
	}
	
	public CellConstraints addCell(int row,int column,Date value){
		return addCell(row, column, value, DEFAULT_DATE_FORMAT);
	}
	
	public CellConstraints addCell(int row,int column,Date value,String format){
		HSSFCell cell=getCell(row,column);
		if (value!=null){
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);
		}
		cell.setCellStyle(getCellStyle(format));
		setSheetSize(currentSheetName,row,column);
		return constructCellConstraints(row,column);
	}
	
	public CellConstraints addCell(int row,int column,double value){
		return addCell(row,column,value,DEFAULT_DOUBLE_FORMAT);
	}
	
	public CellConstraints addCell(int row,int column,double value,String format){
		HSSFCell cell=getCell(row,column);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		CellStyle style=getCellStyle(format);
		style.setAlignment(HORIZONTAL_ALIGN_RIGHT);
		cell.setCellStyle(style);
		setSheetSize(currentSheetName,row,column);
		return constructCellConstraints(row,column);
	}
	/**
	 * HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2, int row2)
	 * 	dx1：the x coordinate within the first cell。
		dy1：the y coordinate within the first cell。
		dx2：the x coordinate within the second cell。
		dy2：the y coordinate within the second cell。
		col1：the column (0 based) of the first cell。
		row1：the row (0 based) of the first cell。
		col2：the column (0 based) of the second cell。
		row2：the row (0 based) of the second cell。
	 */
	public void addPicCell(byte[] buf,short col1,int row1,short col2, int row2){
		HSSFPatriarch patriarch = getCurrentWorksheet().createDrawingPatriarch();
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,  col1, row1, col2, row2);
		anchor.setAnchorType(3);
		patriarch.createPicture(anchor, getWorkBook().addPicture(buf, HSSFWorkbook.PICTURE_TYPE_JPEG));
	}
	
	public CellConstraints addCell(int row,int column,long value){
		HSSFCell cell=getCell(row,column);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		CellStyle style=getCellStyle(DEFAULT_LONG_FORMAT);
		style.setAlignment(HORIZONTAL_ALIGN_RIGHT);
		cell.setCellStyle(style);
		setSheetSize(currentSheetName,row,column);
		return constructCellConstraints(row,column);
	}
	
	public CellConstraints addCell(int row,int column,Number value,String format){
		HSSFCell cell=getCell(row,column);
		if (value!=null){
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value.doubleValue());
		}
		CellStyle style=getCellStyle(format);
		style.setAlignment(HORIZONTAL_ALIGN_RIGHT);
		cell.setCellStyle(style);
		setSheetSize(currentSheetName,row,column);
		return constructCellConstraints(row,column);
	}
	
	public CellConstraints addCell(int row,int column,boolean value){
		return addCell(row,column,Boolean.valueOf(value));
	}
	
	public CellConstraints addCell(int row,int column,Boolean value){
		return addCell(row, column, (String)(value==null?null:getDefaultBooleanText(value)));
//		HSSFCell cell=getCell(row,column);
//		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		if(value!=null){
//			cell.setCellValue(getDefaultBooleanText(value));
//		}
//		return constructCellConstraints(row,column);
	}
	
	public void setDefaultCellBorder(boolean border) {
		ExcelCellStyleHelper.setCellStyleBorder(getDefaultCellStyle(), border);
	}

	public void setDefaultFont(String fontName,short size,boolean bold,boolean italic,boolean underLine,boolean deleteLine) {
		default_font= ExcelCellStyleHelper.createFont(getWorkBook(), fontName, size, bold, italic, underLine, deleteLine);
		getDefaultCellStyle().setFont(default_font);
	}
	
	public void setDefaultHorizontalAlignment(short alignment){
		getDefaultCellStyle().setAlignment(alignment);
		
	}

	public void setDefaultVerticalAlignment(short alignment){
		getDefaultCellStyle().setVerticalAlignment(alignment);
		
	}
	public File getExcelFile(String fileName) throws Exception{
		File file=createStoreFile(fileName);
//		File file=new File("d:/test.xls");
		FileOutputStream fileOut = new FileOutputStream(file);
		autoFillBlankCell();
		autoWorkBookColumnSize();
		getWorkBook().write(fileOut);
	    fileOut.close();
		return file;
	}
	private File createStoreFile(String fileName) throws Exception {
		/*File storedFile = new File("WebRoot" + File.separator 
				+ createStoreFilePath() + File.separator+ fileName);*/
		File storedFile = new File(fileName);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private final static String uploadFilePath="downloadFile";
	private String createStoreFilePath() {
		String storedFilePath = uploadFilePath + File.separator +getFileSuffix().substring(1)
				+ File.separator +"sdfsd";
		File file = new File("WebRoot" + File.separator + storedFilePath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return storedFilePath;
	}

	private String getFileSuffix() {
		return ".xls";
	}
	
	public void writeExcelFileData(OutputStream outputStream) throws IOException{
		autoFillBlankCell();
		autoWorkBookColumnSize();
		getWorkBook().write(outputStream);
	}
	
	private void autoFillBlankCell() {
		for (SheetSize size:sheetSizes){
			autoFillBlankCell(size);
		}
	}

	private void autoFillBlankCell(SheetSize size) {
		HSSFSheet sheet=getWorkBook().getSheet(size.sheetName);
		currentSheetName=size.sheetName;
		if (sheet!=null){
			for (int row=0;row<=size.rowCount;row++){
				for (int column=0;column<=size.columnCount;column++){
					if (!cellExisted(row,column)){
						addCell(row, column, "");
					}
				}
			}
		}
	}

	private void autoWorkBookColumnSize() {
		for (SheetSize size:sheetSizes){
			autoWorkSheetColumnSize(size);
		}
	}

	private void autoWorkSheetColumnSize(SheetSize size) {
		HSSFSheet sheet=getWorkBook().getSheet(size.sheetName);
		if (sheet!=null){
			for (int index=0;index<=size.columnCount;index++){
				sheet.autoSizeColumn(index,true);
			}
		}
	}

	private String getDefaultBooleanText(boolean value){
		return value?"是":"否";
	}
	
	
	public HSSFWorkbook getWorkBook(){
		if (workbook==null){
			workbook=new HSSFWorkbook();
			context=new ExcelWriteContext(workbook);
		}
		return workbook;
	}
	
	private HSSFCell getCell(int row, int column){
		return getCell(getRow(row),column);
	}
	
	private HSSFCell getCell(HSSFRow rowData, int column){
		if (rowData==null) throw new IllegalArgumentException("指定行不存在，无法定位单元格");
		HSSFCell result=rowData.getCell(column);
		if (result==null){
			result=rowData.createCell(column);
		}
		return result;
	}
	
	private boolean cellExisted(int row,int column){
		HSSFRow rowData=getCurrentWorksheet().getRow(row);
		if (rowData==null) return false;
		HSSFCell cell=rowData.getCell(column);
		return cell!=null;
	}
	/**
	 * FIXME
	 * @param row
	 * @return
	 */
	private HSSFRow getRow(int row){
		HSSFRow result=getCurrentWorksheet().getRow(row);
		if (result==null){
			result=getCurrentWorksheet().createRow(row);
		}
		return result;
	}
	
	private HSSFSheet getCurrentWorksheet(){
		if (emptyStringValue(currentSheetName)) throw new IllegalStateException("尚未创建工作表，请先创建相应的工作表");
		return getWorkBook().getSheet(currentSheetName);
	}
	
	private boolean emptyStringValue(String value){
		return value==null || value.trim().length()==0;
	}
	

	private CellStyle getCellStyle(String format) {
		CellStyle cellStyle =getWorkBook().createCellStyle();
		cellStyle.cloneStyleFrom(getDefaultCellStyle());
		cellStyle.setDataFormat(getWorkBook().getCreationHelper().createDataFormat().getFormat(format));
		return cellStyle;
	}
	
	private CellStyle getDefaultCellStyle(){
		if (default_Text_Style==null){
			default_Text_Style=getWorkBook().createCellStyle();
		}
		return default_Text_Style;
	}

	private CellConstraints constructCellConstraints(int row, int column) {
		return new CellConstraints(context,currentSheetName,row,column);
	}

	private void setSheetSize(String sheetName,int row,int column){
		SheetSize sheetsize=lookupSheeiSize(sheetName);
		sheetsize.rowCount=Math.max(sheetsize.rowCount, row);
		sheetsize.columnCount=Math.max(sheetsize.columnCount, column);
	}
	
	private SheetSize lookupSheeiSize(String sheetName) {
		for (SheetSize size:sheetSizes){
			if (size.sheetName!=null && size.sheetName.equals(sheetName)){
				return size;
			}
		}
		SheetSize result=constructSheetSize(sheetName);
		sheetSizes.add(result);
		return result;
	}

	private SheetSize constructSheetSize(String sheetName) {
		return new SheetSize(sheetName);
	}

	class SheetSize{
		private int columnCount=0;
		private int rowCount=0;
		private String sheetName;
		
		SheetSize(String sheetName){
			this.sheetName=sheetName;
		} 
	}
	
	
	public static void main(String[] arg) throws Exception{
		ExcelWriter write=new ExcelWriter();
		write.setDefaultCellBorder(true);
		/*		write.addWorkSheet("测试1");
		write.addCell(0, 0, "ceshi shuju").setFont("宋体", 20, true, true,true,true);
		write.addCell(0, 1, Calendar.getInstance().getTime());
		write.addCell(0, 2, (Integer)null,ExcelWriter.DEFAULT_LONG_FORMAT);
		write.addCell(0, 2, (Boolean)null);
		write.addCell(0, 3, 1234567890.98765);
		write.addCell(0, 4, true);
		write.addCell(0, 5, Boolean.FALSE);
		write.addCell(1, 0, "ceshi shuju").setFont("宋体", 10, false, true,true,true);
		write.addCell(2, 1, Calendar.getInstance().getTime());
		write.addCell(3, 2, -1234567L);
		write.addCell(4, 3, 1234567890.98765).setBorder(true);
		write.addCell(4, 4, false);
		write.addCell(5, 5, "mergedString").mergeTo(8,12).setBorder(true).setFont("宋体", 20, true, true,true,true);
		write.addCell(5, 1, -1234567L).setBorder(true).mergeTo(6,1).setBorder(false);
		write.addCell(5, 2, 1234567890.98765,"#,##0.0000").setBorder(true).mergeTo(6,2);
		write.addCell(5, 3, false).mergeTo(6,3);
		write.addCell(8, 1, "aaaaaaa");*/
		write.addWorkSheet("测试2");
		write.addCell(0, 0, "ceshi shuju");
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		BufferedImage bufferImg = ImageIO.read(new File("B.jpg"));
		ImageIO.write(bufferImg, "jpg", byteArrayOut);
		/*write.addPicCell(byteArrayOut.toByteArray(),(short)1,0,(short)2,1);
		write.addPicCell(byteArrayOut.toByteArray(),(short)1,1,(short)2,2);
		write.addPicCell(byteArrayOut.toByteArray(),(short)1,2,(short)2,3);*/
		write.addPicCell(byteArrayOut.toByteArray(),(short)3,1,(short)4,2);
		write.addPicCell(byteArrayOut.toByteArray(),(short)3,2,(short)4,3);
		write.addPicCell(byteArrayOut.toByteArray(),(short)3,3,(short)4,4);
		write.addCell(0, 1, Calendar.getInstance().getTime());
		write.addCell(0, 2, 1234567L);
		write.addCell(0, 3, 1234567890.98765);
		write.getExcelFile("test.xls");
	}

}
