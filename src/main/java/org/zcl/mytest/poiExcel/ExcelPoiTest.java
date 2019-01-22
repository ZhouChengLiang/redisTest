package org.zcl.mytest.poiExcel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
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

public class ExcelPoiTest {  
  
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
        HSSFWorkbook workbook = new HSSFWorkbook();  // 创建一个excel  
        HSSFSheet sheet = workbook.createSheet("test"); // 为excel创建一个名为test的sheet页
        HSSFRow row = sheet.createRow(0);
        String[] strArr = {"入库时间","产品名","商业公司","批号","单价","数量","金额","有效期","结算时间","实际结算数量","实际结算单价","实际结算金额","票号","税金","备注"};
        for(int i = 0;i<strArr.length;i++){
        	HSSFCell cell = row.createCell(i);
        	cell.setCellValue(strArr[i]);
        }
        for(int i = 1;i < 50 ;i++){
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
        cellStyle.setFont(fontStyle);  
        cellStyle.setAlignment(HorizontalAlignment.CENTER); 
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        cellStyle.setBorderBottom(BorderStyle.THIN);  
//        cellStyle.setBorderLeft(BorderStyle.THIN);  
//        cellStyle.setBorderRight(BorderStyle.THIN);  
//        cellStyle.setBorderTop(BorderStyle.THIN);  
        List<Integer> spanRows = Arrays.asList(2,3,4,5,6,7,2,3,2,1,2,1,3,4,1,3,4);
        Map<Integer,Integer> positions = transPositionMap(spanRows, 1);
        System.out.println("positions>>>>>>>>>>>>>>>>"+positions);
        for(int j = 0;j<8 ;j++){//怎么由2,4,5  ->>> 1,2,3,6,7,11
        	for(Map.Entry<Integer, Integer> entry:positions.entrySet()){
        		CellRangeAddress cra =new CellRangeAddress(entry.getKey(),entry.getValue(), j, j);// 起始行, 终止行, 起始列, 终止列  
                sheet.addMergedRegion(cra); 
//                RegionUtil.setBorderBottom(1, cra, sheet); // 下边框  
//                RegionUtil.setBorderLeft(1, cra, sheet); // 左边框  
//                RegionUtil.setBorderRight(1, cra, sheet); // 有边框  
//                RegionUtil.setBorderTop(1, cra, sheet); // 上边框  
                
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
    
    
}
class AgentSettle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1925078424573982052L;
	
	public AgentSettle(){}
	
	public AgentSettle(Integer stockId, String batchNo, Date stockTime, Double unitPrice, Integer amount,
			Double totalMoney, Date expiryTime, String drugShowName, String dispatchName, Integer actualAmount,
			Double actualUnitPrice, Double actualMoney, Double actualTax, String ticketNo, Date settleTime,
			String remark) {
		this.stockId = stockId;
		this.batchNo = batchNo;
		this.stockTime = stockTime;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.totalMoney = totalMoney;
		this.expiryTime = expiryTime;
		this.drugShowName = drugShowName;
		this.dispatchName = dispatchName;
		this.actualAmount = actualAmount;
		this.actualUnitPrice = actualUnitPrice;
		this.actualMoney = actualMoney;
		this.actualTax = actualTax;
		this.ticketNo = ticketNo;
		this.settleTime = settleTime;
		this.remark = remark;
	}

	/**
	 * 库存结算明细id
	 */
	private Integer id;
	
	/**
	 * 库存明细id
	 */
	private Integer stockId;
	
	/**
	 * 批号
	 */
	private String batchNo;
	
	/**
	 * 入库时间
	 */
	private Date stockTime;
	
	/**
	 * 跨行数 
	 */
	private Integer spanRows;
	
	/**
	 * 单价
	 */
	private Double unitPrice;
	
	/**
	 * 数量
	 */
	private Integer amount;
	
	/**
	 * 金额
	 */
	private Double totalMoney;
	
	/**
	 * 有效期
	 */
	private Date expiryTime;
	
	/**
     * 药品名称
     */
    private String drugName;

    /**
     * 药品规格
     */
    private String  drugSpec;

    /**
     * 药品商品名
     */
    private String  drugProductName;

    /**
     * 药品显示名 格式：drug_name(product_name) | spec | factory_name
     */
    private String  drugShowName;

    /**
     * 药品id
     */
    private Integer drugId;

    /**
     * 商业公司id
     */
    private Integer dispatchId;

    /**
     * 商业公司名称
     */
    private String dispatchName;

    /**
     * 厂家名称
     */
    private String  factoryName;
    
    /**
     * 转化比
     */
    private Integer transitionRatio;
    
    /**
     * 实际结算数量
     */
    private Integer actualAmount;
    
    /**
     * 实际结算单价
     */
    private Double actualUnitPrice;
    
    /**
     * 实际结算金额
     */
    private Double actualMoney;
    
    /**
     * 实际结算税金
     */
    private Double actualTax;
    
    /**
     * 票号
     */
    private String ticketNo;
    
    /**
     * 结算状态(0 未结算 1 已结算)
     */
    private Integer status;
    
    /**
     * 结算时间
     */
    private Date settleTime;
    
    /**
     * 备注
     */
    private String remark;
	
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建修改人
     */
    private Integer operatorId;
    
    /**
     * 给页脚提供汇总数据 数量
     */
    private Integer finalAmount;
    
    /**
     * 给页脚提供汇总数据 金额
     */
    private Double finalMoney;
    
    /**
     * 给页脚提供汇总数据 税金
     */
    private Double finalTax;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}

	public String getDrugProductName() {
		return drugProductName;
	}

	public void setDrugProductName(String drugProductName) {
		this.drugProductName = drugProductName;
	}

	public String getDrugShowName() {
		return drugShowName;
	}

	public void setDrugShowName(String drugShowName) {
		this.drugShowName = drugShowName;
	}

	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getDispatchName() {
		return dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getTransitionRatio() {
		return transitionRatio;
	}

	public void setTransitionRatio(Integer transitionRatio) {
		this.transitionRatio = transitionRatio;
	}

	public Integer getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Double getActualUnitPrice() {
		return actualUnitPrice;
	}

	public void setActualUnitPrice(Double actualUnitPrice) {
		this.actualUnitPrice = actualUnitPrice;
	}

	public Double getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(Double actualMoney) {
		this.actualMoney = actualMoney;
	}

	public Double getActualTax() {
		return actualTax;
	}

	public void setActualTax(Double actualTax) {
		this.actualTax = actualTax;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}
	
	@Override
	public String toString() {
		return "AgentSettle [id=" + id + ", stockId=" + stockId + ", batchNo=" + batchNo + ", spanRows=" + spanRows
				+ "]";
	}

	public Integer getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Integer finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Double getFinalMoney() {
		return finalMoney;
	}

	public void setFinalMoney(Double finalMoney) {
		this.finalMoney = finalMoney;
	}

	public Double getFinalTax() {
		return finalTax;
	}

	public void setFinalTax(Double finalTax) {
		this.finalTax = finalTax;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getStockTime() {
		return stockTime;
	}

	public void setStockTime(Date stockTime) {
		this.stockTime = stockTime;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	public Integer getSpanRows() {
		return spanRows;
	}

	public void setSpanRows(Integer spanRows) {
		this.spanRows = spanRows;
	}
	
}