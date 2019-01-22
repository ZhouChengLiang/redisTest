/**
 * Yztz.com Inc. Copyright (c) 2013-2015 All Rights Reserved.
 */
package org.zcl.mytest.poiExcel;

/**
 * @author hao
 * @version $Id: XLSUtils.java, v 0.1 Mar 26, 2015 1:24:10 PM hao Exp $
 */
public class ExcelUtil {
//
//
//  /**
//   * 读取单元格的值
//   */
//  public static String getCellValue(Cell cell) {
//    DecimalFormat df = new DecimalFormat("0");
//
//    Object result = "";
//
//    if (cell != null) {
//      switch (cell.getCellType()) {
//        case Cell.CELL_TYPE_STRING:
//          result = cell.getStringCellValue();
//          break;
//        case Cell.CELL_TYPE_NUMERIC:
//          if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
////                    DataFormatter formatter=new DataFormatter();
//            return DateUtil.format(
//                org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()),
//                "yyyy-MM-dd");
//          } else {
//            result = df.format(cell.getNumericCellValue());
//          }
//          break;
//        case Cell.CELL_TYPE_BOOLEAN:
//          result = cell.getBooleanCellValue();
//          break;
//        case Cell.CELL_TYPE_FORMULA:
//          result = cell.getCellFormula();
//          break;
//        case Cell.CELL_TYPE_ERROR:
//          result = cell.getErrorCellValue();
//          break;
//        case Cell.CELL_TYPE_BLANK:
//          break;
//        default:
//          break;
//      }
//    }
//    return result.toString();
//  }
//
//  /**
//   * 读取单元格的时间格式数据
//   */
//  public static String getCellDateValue(Cell cell) {
//    DecimalFormat df = new DecimalFormat("0");
//
//    Object result = "";
//
//    if (cell != null) {
//      switch (cell.getCellType()) {
//        case Cell.CELL_TYPE_STRING:
//          result = DateUtil.format(DateUtil.parseAllDate(cell.getStringCellValue()));
//          break;
//        case Cell.CELL_TYPE_NUMERIC:
//          if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
////                    DataFormatter formatter=new DataFormatter();
//            return DateUtil.format(
//                org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue()),
//                "yyyy-MM-dd");
//          } else {
//            result = df.format(cell.getNumericCellValue());
//          }
//          break;
//        default:
//          break;
//      }
//    }
//    return result.toString();
//  }
//
//  public static List<Row> readExcel_xlsx(InputStream inputStream) throws IOException {
//    if (inputStream == null) {
//      throw new IOException("文件不存在！");
//    }
//
//    XSSFWorkbook wb = null;
//    List<Row> rowList = new ArrayList<Row>();
//    // 去读Excel
//    wb = new XSSFWorkbook(inputStream);
//
//    // 读取Excel 2007版，xlsx格式
//    rowList = readExcel(wb);
//
//    return rowList;
//  }
//
//  /**
//   * //读取Excel 2007版，xlsx格式
//   *
//   * @Title: readExcel_xlsx
//   * @Date : 2014-9-11 上午11:43:11
//   */
//  public static List<Row> readExcel_xlsx(String xlsPath) throws IOException {
//    // 判断文件是否存在
//    File file = new File(xlsPath);
//    if (!file.exists()) {
//      throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
//    }
//    XSSFWorkbook wb = null;
//    List<Row> rowList = new ArrayList<Row>();
//    FileInputStream fis = new FileInputStream(file);
//    // 去读Excel
//    wb = new XSSFWorkbook(fis);
//
//    // 读取Excel 2007版，xlsx格式
//    rowList = readExcel(wb);
//
//    return rowList;
//  }
//
//  /**
//   * inputstream
//   */
//  public static List<Row> readExcel_xls(InputStream inputStream) throws IOException {
//    if (inputStream == null) {
//      throw new IOException("文件名不存在！");
//    }
//    HSSFWorkbook wb = null;// 用于Workbook级的操作，创建、删除Excel
//    List<Row> rowList = new ArrayList<Row>();
//
//    // 读取Excel
//    wb = new HSSFWorkbook(inputStream);
//
//    // 读取Excel 97-03版，xls格式
//    rowList = readExcel(wb);
//
//    return rowList;
//  }
//
//  /***
//   * 读取Excel(97-03版，xls格式)
//   *
//   * @throws Exception
//   * @Title: readExcel
//   * @Date : 2014-9-11 上午09:53:21
//   */
//  public static List<Row> readExcel_xls(String xlsPath) throws IOException {
//
//    // 判断文件是否存在
//    File file = new File(xlsPath);
//    if (!file.exists()) {
//      throw new IOException("文件名为" + file.getName() + "Excel文件不存在！");
//    }
//
//    HSSFWorkbook wb = null;// 用于Workbook级的操作，创建、删除Excel
//    List<Row> rowList = new ArrayList<Row>();
//
//    // 读取Excel
//    wb = new HSSFWorkbook(new FileInputStream(file));
//
//    // 读取Excel 97-03版，xls格式
//    rowList = readExcel(wb);
//
//    return rowList;
//  }
//
//  /**
//   * 读取excel信息（支持xls，xlsx）
//   */
//  public static List<Row> readExcel_common(InputStream inputStream, Integer sheetIndex)
//      throws Exception {
//
//    // 判断文件是否存在
//    if (inputStream == null) {
//      throw new IOException("文件名不存在！");
//    }
//
//    Workbook wb = null;// 用于Workbook级的操作，创建、删除Excel
//    List<Row> rowList = new ArrayList<Row>();
//
//    try {
//      // 读取Excel
//      wb = WorkbookFactory.create(inputStream);
//
//      // 读取excel行数据
//      rowList = readExcel(wb, sheetIndex);
//    } catch (Exception e) {
//      throw new Exception("excel读取失败");
//    } finally {
//      if (wb != null) {
//        try {
//          wb.close();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//    }
//
//    return rowList;
//  }
//
//  /**
//   * 读取excel每行数据
//   *
//   * @param sheetIndex 读取第几个sheet
//   */
//  private static List<Row> readExcel(Workbook wb, Integer sheetIndex) {
//    List<Row> rowList = new ArrayList<Row>();
//
//    Sheet sheet = wb.getSheetAt(sheetIndex);
//
//    // 获取最后行号
//    int lastRowNum = sheet.getLastRowNum();
//
//    Row row = null;
//    // 循环读取
//    for (int i = 0; i <= lastRowNum; i++) {
//      row = sheet.getRow(i);
//      if (row != null) {
//        rowList.add(row);
//
//      }
//    }
//
//    return rowList;
//  }
//
//  /**
//   * 通用读取Excel
//   *
//   * @Title: readExcel
//   * @Date : 2014-9-11 上午11:26:53
//   */
//  private static List<Row> readExcel(Workbook wb) {
//    List<Row> rowList = new ArrayList<Row>();
//    Sheet sheet = null;
//    int sheetCount = wb.getNumberOfSheets();// 获取可以操作的总数量
//
//    // 获取sheet数目
//    for (int t = 0; t < sheetCount; t++) {
//      // 获取设定操作的sheet
//      sheet = wb.getSheetAt(t);
//
//      // 获取最后行号
//      int lastRowNum = sheet.getLastRowNum();
//
//      Row row = null;
//      // 循环读取
//      for (int i = 0; i <= lastRowNum; i++) {
//        row = sheet.getRow(i);
//        if (row != null) {
//          rowList.add(row);
//
//        }
//      }
//    }
//    return rowList;
//  }
//
//  /**
//   * 1.创建 workbook
//   */
//  public static HSSFWorkbook getHSSFWorkbook() {
//    return new HSSFWorkbook();
//  }
//
//  /*
//   * 1.通过已有的excel创建workbook
//   *
//   */
//  public static HSSFWorkbook getHSSFWorkbook(String path) throws FileNotFoundException {
//    HSSFWorkbook hssfWorkbook = null;
//    FileInputStream fileInputStream = null;
//    File file = new File(path);
//    try {
//      if (file != null) {
//        fileInputStream = new FileInputStream(file);
//        hssfWorkbook = new HSSFWorkbook(fileInputStream);
//      }
//    } catch (Exception e) {
//      throw new FileNotFoundException(Constants.FLOW_UPLOAD_NORMAL_NAME + "Excel不存在");
//    } finally {
//      if (fileInputStream != null) {
//        try {
//          fileInputStream.close();
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//    }
//    return hssfWorkbook;
//  }
//
//  /**
//   * 1.创建x workbook
//   */
//  public static XSSFWorkbook getXSSFWorkbook() {
//    return new XSSFWorkbook();
//  }
//
//  /**
//   * 2.创建 sheet
//   *
//   * @param sheetName sheet 名称
//   */
//  public static HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook, String sheetName) {
//    HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);
//    return hssfSheet;
//  }
//
//  /**
//   * 2.获得已有的sheet，若无则新建
//   */
//  public static HSSFSheet getExistHSSFSheet(HSSFWorkbook hssfWorkbook, String sheetName) {
//    HSSFSheet hssfSheet = null;
//    hssfSheet = hssfWorkbook.getSheet(sheetName);
//    if (hssfSheet == null) {
//      hssfSheet = hssfWorkbook.createSheet(sheetName);
//    }
//    return hssfSheet;
//  }
//
//  /**
//   * 2.创建 sheet
//   *
//   * @param sheetName sheet 名称
//   */
//  public static XSSFSheet getXSSFSheet(XSSFWorkbook xssfWorkbook, String sheetName) {
//    return xssfWorkbook.createSheet(sheetName);
//  }
//
//  /**
//   * 3.写入销售报表表头信息
//   *
//   * @param headInfoList List<Map<String, Object>> key: title 列标题 columnWidth 列宽 dataKey 列对应的
//   * dataList item key
//   */
//  @SuppressWarnings({"static-access", "rawtypes"})
//  public void writeSaleStatementHeader(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet,
//      String[] headInfoList, List dataList) {
//    HSSFCellStyle cs = hssfWorkbook.createCellStyle();
//    HSSFFont font = hssfWorkbook.createFont();
//    font.setFontHeightInPoints((short) 10);
//    font.setBoldweight(font.BOLDWEIGHT_BOLD);
//    cs.setFont(font);
//    cs.setAlignment(cs.ALIGN_CENTER);
//
//    HSSFRow r = hssfSheet.createRow(0);
//    HSSFRow r1 = hssfSheet.createRow(1);
//    r.setHeight((short) 380);
//    HSSFCell c = null;
//    String headInfo = null;
//    // 处理excel表头
//    // 销售报表导出(表头有合并情况)
//    // 合并列前的表头
//    String[] title = {"数量", "金额"};
//    for (int i = 0, len = headInfoList.length; i < len - 2; i++) {
//      headInfo = headInfoList[i];
//      c = r.createCell(i);
//      c.setCellValue(headInfo);
//      c.setCellStyle(cs);
//    }
//    // 合并列的表头
//    for (int i = 0;
//        i < ((AgentSaleStatement) dataList.get(0)).getSaleStatementInfos().size(); i++) {
//      // 时间 列:2018-03
//      headInfo = ((AgentSaleStatement) dataList.get(0)).getSaleStatementInfos().get(i)
//          .getMonth();
//      c = r.createCell(headInfoList.length - 2 + i * 2);
//      c.setCellValue(headInfo);
//      c.setCellStyle(cs);
//
//      // 数量 金额
//      for (int j = 0; j < title.length; j++) {
//        c = r1.createCell(headInfoList.length - 2 + i * 2 + j);
//        c.setCellValue(title[j]);
//        c.setCellStyle(cs);
//      }
//    }
//    // 合并列后的表头
//    // j合并列的数量
//    int j = ((AgentSaleStatement) dataList.get(0)).getSaleStatementInfos().size();
//    // i不合并的表头在表头数组中的序号
//    for (int i = 6, len = headInfoList.length; i < len; i++) {
//      headInfo = headInfoList[i];
//      c = r.createCell(i + 2 * j);
//      c.setCellValue(headInfo);
//      c.setCellStyle(cs);
//    }
//
////            CellRangeAddress（int， int， int， int）
////            参数：起始行号，终止行号， 起始列号，终止列号
//    // 合并列
//    for (int i = 0;
//        i < ((AgentSaleStatement) dataList.get(0)).getSaleStatementInfos().size(); i++) {
//      hssfSheet.addMergedRegion(
//          new CellRangeAddress(0, 0, (headInfoList.length - 2 + i * 2),
//              (headInfoList.length - 2 + i * 2 + 1)));
//    }
//    // 合并行1
//    for (int i = 0, len = headInfoList.length; i < len - 2; i++) {
//      hssfSheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
//    }
//    // 合并行2
//    for (int i = 6, len = headInfoList.length; i < len; i++) {
//      hssfSheet.addMergedRegion(new CellRangeAddress(0, 1, i + 2 * j, i + 2 * j));
//    }
//  }
//
//  /**
//   * 3.写入表头信息
//   *
//   * @param headInfoList List<Map<String, Object>> key: title 列标题 columnWidth 列宽 dataKey 列对应的
//   * dataList item key
//   */
//  @SuppressWarnings("static-access")
//  public void writeHeader(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, String[] headInfoList) {
//    HSSFCellStyle cs = hssfWorkbook.createCellStyle();
//    HSSFFont font = hssfWorkbook.createFont();
//    font.setFontHeightInPoints((short) 10);
//    font.setBoldweight(font.BOLDWEIGHT_BOLD);
//    cs.setFont(font);
//    cs.setAlignment(cs.ALIGN_CENTER);
//
//    HSSFRow r = hssfSheet.createRow(0);
//    r.setHeight((short) 380);
//    HSSFCell c = null;
//    String headInfo = null;
//    // 处理excel表头
//    for (int i = 0, len = headInfoList.length; i < len; i++) {
//      headInfo = headInfoList[i];
//      c = r.createCell(i);
//      c.setCellValue(headInfo);
//      c.setCellStyle(cs);
//
////            if (headInfo.containsKey("columnWidth")) {
////                hssfSheet.setColumnWidth(i, (short) (((Integer) headInfo.get("columnWidth") * 8) / ((double) 1 / 20)));
////            }
//    }
//  }
//
//  /**
//   * 4.写入销售报表内容部分
//   *
//   * @param hssfSheet
//   * @param startIndex
//   * @param clazz
//   * @param fields
//   * @param dataList
//   * @param headInfoList
//   */
//  @SuppressWarnings("rawtypes")
//  public void writeSaleStatementContent(HSSFSheet hssfSheet, int startIndex, Class clazz,
//      List<String> fields, List dataList, String pattern, String[] headInfoList) {
//    HSSFRow r = null;
//    HSSFCell c = null;
//    // 处理数据
//    Object dataItem = null;
//    Object v = null;
//    int index = 0;
//    for (int i = 0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len;
//        i++, rownum++, index++) {
//      r = hssfSheet.createRow(rownum);
//      r.setHeightInPoints(16);
//      dataItem = dataList.get(i);
//
//      String temp = null;
//      String headInfo = null;
//
//      // 1.合并列前面部分
//      // jlen 列数
//      for (int j = 0, jlen = fields.size(); j < jlen - 2; j++) {
//        c = r.createCell(j);
//
//        v = ReferUtil.getReflectValue(dataItem, fields.get(j));
//
//        if (v == null) {
//          c.setCellValue("");
//          continue;
//        }
//
//        if (v instanceof String) {
//          c.setCellValue((String) v);
//        } else if (v instanceof Boolean) {
//          c.setCellValue((Boolean) v);
//        } else if (v instanceof Calendar) {
//          c.setCellValue((Calendar) v);
//        } else if (v instanceof Double) {
//          c.setCellValue((Double) v);
//        } else if (v instanceof Integer || v instanceof Long || v instanceof Short
//            || v instanceof Float) {
//          c.setCellValue(Double.parseDouble(v.toString()));
//        } else if (v instanceof Date) {
//          temp = DateUtil.format((Date) v, pattern);
//          c.setCellValue(temp);
//        } else if (v instanceof HSSFRichTextString) {
//          c.setCellValue((HSSFRichTextString) v);
//        } else {
//          c.setCellValue(v.toString());
//        }
//
//        if (v != null) {
//          if (v instanceof String || v instanceof Date) {
//            if (temp != null) {
//              hssfSheet.setColumnWidth(j, temp.getBytes().length * 256);
//            } else {
//              headInfo = headInfoList[j];
//              if (headInfo.length() > v.toString().length()) {
//                hssfSheet.setColumnWidth(j, headInfo.getBytes().length * 256);
//              } else {
//                if (v.toString().getBytes().length >= 256) {
//                  hssfSheet.setColumnWidth(j, 10 * 256);
//                } else {
//                  hssfSheet.setColumnWidth(j, v.toString().getBytes().length * 384);
//                }
//              }
//            }
//
//          } else {
//            headInfo = headInfoList[j];
//            int size = 1;
//            if (headInfo.length() <= 2) {
//              size = 2;
//            }
//            hssfSheet.setColumnWidth(j, headInfo.getBytes().length * size * 384);
//          }
//        }
//
//        temp = null;
//        headInfo = null;
//      }
//
//      // 2.合并列后面部分
//      // jlen 列数
//      // sizeTemp合并区域数量
//      int sizeTemp = ((AgentSaleStatement) dataList.get(0)).getSaleStatementInfos().size();
//      for (int j = fields.size() + sizeTemp * 2, jlen = fields.size() + sizeTemp * 2;
//          j > jlen - 2; j--) {
//        c = r.createCell(j - 1);
//
//        v = ReferUtil.getReflectValue(dataItem, fields.get(j - sizeTemp * 2 - 1));
//
//        if (v == null) {
//          c.setCellValue("");
//          continue;
//        }
//
//        if (v instanceof String) {
//          c.setCellValue((String) v);
//        } else if (v instanceof Boolean) {
//          c.setCellValue((Boolean) v);
//        } else if (v instanceof Calendar) {
//          c.setCellValue((Calendar) v);
//        } else if (v instanceof Double) {
//          c.setCellValue((Double) v);
//        } else if (v instanceof Integer || v instanceof Long || v instanceof Short
//            || v instanceof Float) {
//          c.setCellValue(Double.parseDouble(v.toString()));
//        } else if (v instanceof Date) {
//          temp = DateUtil.format((Date) v, pattern);
//          c.setCellValue(temp);
//        } else if (v instanceof HSSFRichTextString) {
//          c.setCellValue((HSSFRichTextString) v);
//        } else {
//          c.setCellValue(v.toString());
//        }
//
//        if (v != null) {
//          if (v instanceof String || v instanceof Date) {
//            if (temp != null) {
//              hssfSheet.setColumnWidth(j, temp.getBytes().length * 256);
//            } else {
//              headInfo = headInfoList[j];
//              if (headInfo.length() > v.toString().length()) {
//                hssfSheet.setColumnWidth(j, headInfo.getBytes().length * 256);
//              } else {
//                if (v.toString().getBytes().length >= 256) {
//                  hssfSheet.setColumnWidth(j, 10 * 256);
//                } else {
//                  hssfSheet.setColumnWidth(j, v.toString().getBytes().length * 256);
//                }
//              }
//            }
//
//          } else {
//            headInfo = headInfoList[j - sizeTemp * 2 - 1];
//            int size = 1;
//            if (headInfo.length() <= 2) {
//              size = 2;
//            }
//            hssfSheet.setColumnWidth(j, headInfo.getBytes().length * size * 256);
//          }
//        }
//
//        temp = null;
//        headInfo = null;
//      }
//
//      // 3合并部分数据填充   j合并部分开始的列号
//      for (int j = 6, o = 0; o < sizeTemp; j++, o++) {
//        c = r.createCell(j);
//        Double amount = ((AgentSaleStatement) dataList.get(index)).getSaleStatementInfos()
//            .get(o).getAmount();
//        Integer num = ((AgentSaleStatement) dataList.get(index)).getSaleStatementInfos()
//            .get(o).getNum();
//        c.setCellValue(num);
//        j++;
//        c = r.createCell(j);
//        c.setCellValue(amount);
//      }
//
//    }
//  }
//
//  /**
//   * 4.写入内容部分
//   */
//  @SuppressWarnings("rawtypes")
//  public void writeContent(HSSFSheet hssfSheet, int startIndex, Class clazz, List<String> fields,
//      List dataList, String pattern, String[] headInfoList) {
//    HSSFRow r = null;
//    HSSFCell c = null;
//    // 处理数据
//    Object dataItem = null;
//    Object v = null;
//    for (int i = 0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len;
//        i++, rownum++) {
//      r = hssfSheet.createRow(rownum);
//      r.setHeightInPoints(16);
//      dataItem = dataList.get(i);
//
//      String temp = null;
//      String headInfo = null;
//
//      for (int j = 0, jlen = fields.size(); j < jlen; j++) {
//        c = r.createCell(j);
//
//        v = ReferUtil.getReflectValue(dataItem, fields.get(j));
//
//        if (v == null) {
//          c.setCellValue("");
//          continue;
//        }
//
//        if (v instanceof String) {
//          c.setCellValue((String) v);
//        } else if (v instanceof Boolean) {
//          c.setCellValue((Boolean) v);
//        } else if (v instanceof Calendar) {
//          c.setCellValue((Calendar) v);
//        } else if (v instanceof Double) {
//          c.setCellValue((Double) v);
//        } else if (v instanceof Integer || v instanceof Long || v instanceof Short
//            || v instanceof Float) {
//          c.setCellValue(Double.parseDouble(v.toString()));
//        } else if (v instanceof Date) {
//          temp = DateUtil.format((Date) v, pattern);
//          c.setCellValue(temp);
//        } else if (v instanceof HSSFRichTextString) {
//          c.setCellValue((HSSFRichTextString) v);
//        } else {
//          c.setCellValue(v.toString());
//        }
//
//        // 宽度自适应
//        if (v != null) {
//          if (v instanceof String || v instanceof Date) {
//            if (temp != null) {
//              hssfSheet.setColumnWidth(j, temp.getBytes().length * 256);
//            } else {
//              headInfo = headInfoList[j];
//              if (headInfo.length() > v.toString().length()) {
//                hssfSheet.setColumnWidth(j, headInfo.getBytes().length * 256);
//              } else {
//                if (v.toString().getBytes().length >= 256) {
//                  hssfSheet.setColumnWidth(j, 10 * 256);
//                } else {
//                  hssfSheet.setColumnWidth(j, v.toString().getBytes().length * 256);
//                }
//              }
//            }
//
//          } else {
//            headInfo = headInfoList[j];
//            int size = 1;
//            if (headInfo.length() <= 2) {
//              size = 2;
//            }
//            hssfSheet.setColumnWidth(j, headInfo.getBytes().length * size * 256);
//          }
//        }
//
//        temp = null;
//        headInfo = null;
//      }
//    }
//  }
//
//  /**
//   * 5.写入销售报表汇总信息
//   *
//   * @param hssfSheet
//   * @param rowIndex
//   * @param colIndex
//   * @param clazz
//   * @param fields
//   * @param object
//   */
//  @SuppressWarnings({"rawtypes", "static-access"})
//  public void writeSaleStatementFooter(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, int rowIndex,
//      int colIndex, Class clazz, List<String> fields, Object object) {
//
//    HSSFRow r = null;
//    HSSFCell c = null;
//
//    HSSFCellStyle cs = hssfWorkbook.createCellStyle();
//    HSSFFont font = hssfWorkbook.createFont();
//    font.setFontHeightInPoints((short) 10);
//    font.setBoldweight(font.BOLDWEIGHT_BOLD);
//    cs.setFont(font);
//    cs.setAlignment(cs.ALIGN_RIGHT);
//
//    // 处理数据
//    Object v = null;
//    r = hssfSheet.createRow(rowIndex);
//    r.setHeightInPoints(16);
//
//    c = r.createCell(colIndex - 1);
//    c.setCellValue("总计：");
//    c.setCellStyle(cs);
//
//    int size = ((AgentSaleStatement) object).getSaleStatementInfos().size();
////        List<SaleStatementInfo> infos = ((AgentSaleStatement) object)
////            .getSaleStatementInfos();
//    List<SaleStatementInfo> infos = (List<SaleStatementInfo>) ReferUtil
//        .getReflectValue(object, "saleStatementInfos");
//
//    // 明细汇总填充
//    for (int i = 0, j = 0; i < size; i++, j++) {
//      // 数量
//      c = r.createCell(j + colIndex);
//      v = infos.get(i).getNum();
//
//      if (v instanceof String) {
//        c.setCellValue((String) v);
//      } else if (v instanceof Boolean) {
//        c.setCellValue((Boolean) v);
//      } else if (v instanceof Calendar) {
//        c.setCellValue((Calendar) v);
//      } else if (v instanceof Double) {
//        c.setCellValue((Double) v);
//      } else if (v instanceof Integer || v instanceof Long || v instanceof Short
//          || v instanceof Float) {
//        c.setCellValue(Double.parseDouble(v.toString()));
//      } else if (v instanceof HSSFRichTextString) {
//        c.setCellValue((HSSFRichTextString) v);
//      } else {
//        c.setCellValue(v.toString());
//      }
//
//      c.setCellStyle(cs);
//
//      // 金额
//      j++;
//      c = r.createCell(j + colIndex);
//      v = infos.get(i).getAmount();
//
//      if (v instanceof String) {
//        c.setCellValue((String) v);
//      } else if (v instanceof Boolean) {
//        c.setCellValue((Boolean) v);
//      } else if (v instanceof Calendar) {
//        c.setCellValue((Calendar) v);
//      } else if (v instanceof Double) {
//        c.setCellValue((Double) v);
//      } else if (v instanceof Integer || v instanceof Long || v instanceof Short
//          || v instanceof Float) {
//        c.setCellValue(Double.parseDouble(v.toString()));
//      } else if (v instanceof HSSFRichTextString) {
//        c.setCellValue((HSSFRichTextString) v);
//      } else {
//        c.setCellValue(v.toString());
//      }
//
//      c.setCellStyle(cs);
//    }
//
//    // 总计汇总填充
//    int o = size;
//    c = r.createCell(o + colIndex + size);
////      Double amount = ((AgentSaleStatement) object).getAmount();
//    Double amount = (Double) ReferUtil.getReflectValue(object, fields.get(fields.size() - 1));
////      Integer num = ((AgentSaleStatement) object).getNum();
//    Integer num = (Integer) ReferUtil.getReflectValue(object, fields.get(fields.size() - 2));
//    c.setCellValue(num);
//    c.setCellStyle(cs);
//    o++;
//    c = r.createCell(o + colIndex + size);
//    c.setCellValue(amount);
//    c.setCellStyle(cs);
//  }
//
//  /**
//   * 5.写入汇总信息
//   */
//  @SuppressWarnings({"rawtypes", "static-access"})
//  public void writeFooter(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, int rowIndex,
//      int colIndex, Class clazz, List<String> fields, Object object) {
//
//    HSSFRow r = null;
//    HSSFCell c = null;
//
//    HSSFCellStyle cs = hssfWorkbook.createCellStyle();
//    HSSFFont font = hssfWorkbook.createFont();
//    font.setFontHeightInPoints((short) 10);
//    font.setBoldweight(font.BOLDWEIGHT_BOLD);
//    cs.setFont(font);
//    cs.setAlignment(cs.ALIGN_RIGHT);
//
//    // 处理数据
//    Object v = null;
//    r = hssfSheet.createRow(rowIndex);
//    r.setHeightInPoints(16);
//
//    c = r.createCell(colIndex - 1);
//    c.setCellValue("总计：");
//    c.setCellStyle(cs);
//
//    for (int j = 0, jlen = fields.size(); j < jlen; j++) {
//      c = r.createCell(j + colIndex);
//
//      v = ReferUtil.getReflectValue(object, fields.get(j));
//
//      if (v == null) {
//        c.setCellValue("");
//        continue;
//      }
//
//      if (v instanceof String) {
//        c.setCellValue((String) v);
//      } else if (v instanceof Boolean) {
//        c.setCellValue((Boolean) v);
//      } else if (v instanceof Calendar) {
//        c.setCellValue((Calendar) v);
//      } else if (v instanceof Double) {
//        c.setCellValue((Double) v);
//      } else if (v instanceof Integer || v instanceof Long || v instanceof Short
//          || v instanceof Float) {
//        c.setCellValue(Double.parseDouble(v.toString()));
//      } else if (v instanceof HSSFRichTextString) {
//        c.setCellValue((HSSFRichTextString) v);
//      } else {
//        c.setCellValue(v.toString());
//      }
//
//      c.setCellStyle(cs);
//    }
//
//  }
//
//  public static void setAutoSizeColumn(HSSFSheet hssfSheet, List<String> fields) {
//    for (int i = 0; i < fields.size(); i++) {
//      hssfSheet.autoSizeColumn(i, true);
//    }
//  }
//
////    public static void writeContent2(HSSFSheet hssfSheet, int startIndex, List<List<Object>> dataList) {
////        HSSFRow r = null;
////        HSSFCell c = null;
////        // 处理数据
////        List<Object> dataItem = null;
////        Object v = null;
////        for (int i = 0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len; i++, rownum++) {
////            r = hssfSheet.createRow(rownum);
////            r.setHeightInPoints(16);
////            dataItem = dataList.get(i);
////            for (int j = 0, jlen = dataItem.size(); j < jlen; j++) {
////                c = r.createCell(j);
////                v = dataItem.get(j);
////
////                if (v instanceof String) {
////                    c.setCellValue((String) v);
////                } else if (v instanceof Boolean) {
////                    c.setCellValue((Boolean) v);
////                } else if (v instanceof Calendar) {
////                    c.setCellValue((Calendar) v);
////                } else if (v instanceof Double) {
////                    c.setCellValue((Double) v);
////                } else if (v instanceof Integer || v instanceof Long || v instanceof Short || v instanceof Float) {
////                    c.setCellValue(Double.parseDouble(v.toString()));
////                } else if (v instanceof HSSFRichTextString) {
////                    c.setCellValue((HSSFRichTextString) v);
////                } else {
////                    c.setCellValue(v.toString());
////                }
////            }
////        }
////    }
//
////    public static void writeContent3(Sheet sheet, int startIndex, List<Object> dataList) {
////        Row r = null;
////        Cell c = null;
////        // 处理数据
////        Object v = null;
////        r = sheet.createRow(startIndex);
////        r.setHeightInPoints(16);
////        for (int j = 0, jlen = dataList.size(); j < jlen; j++) {
////            c = r.createCell(j);
////            v = dataList.get(j);
////
////            if (v instanceof String) {
////                c.setCellValue((String) v);
////            } else if (v instanceof Boolean) {
////                c.setCellValue((Boolean) v);
////            } else if (v instanceof Calendar) {
////                c.setCellValue((Calendar) v);
////            } else if (v instanceof Double) {
////                c.setCellValue((Double) v);
////            } else if (v instanceof Integer || v instanceof Long || v instanceof Short || v instanceof Float) {
////                c.setCellValue(Double.parseDouble(v.toString()));
////            } else if (v instanceof HSSFRichTextString) {
////                c.setCellValue((HSSFRichTextString) v);
////            } else if (v != null) {
////                c.setCellValue(v.toString());
////            }
////        }
////    }
//
//  public static void write2FilePath(HSSFWorkbook hssfWorkbook, String filePath) throws IOException {
//    FileOutputStream fileOut = null;
//    try {
//      fileOut = new FileOutputStream(filePath);
//      hssfWorkbook.write(fileOut);
//    } finally {
//      if (fileOut != null) {
//        fileOut.close();
//      }
//    }
//  }
//
//  public static void write2OutputStream(Workbook workbook, OutputStream fileOut)
//      throws IOException {
//    workbook.write(fileOut);
//  }
//
//  /**
//   * 导出excel code example: List<Map<String, Object>> headInfoList = new
//   * ArrayList<Map<String,Object>>(); Map<String, Object> itemMap = new
//   * HashMap<String, Object>(); itemMap.put("title", "序号1");
//   * itemMap.put("columnWidth", 25); itemMap.put("dataKey", "XH1");
//   * headInfoList.add(itemMap);
//   *
//   * itemMap = new HashMap<String, Object>(); itemMap.put("title", "序号2");
//   * itemMap.put("columnWidth", 50); itemMap.put("dataKey", "XH2");
//   * headInfoList.add(itemMap);
//   *
//   * itemMap = new HashMap<String, Object>(); itemMap.put("title", "序号3");
//   * itemMap.put("columnWidth", 25); itemMap.put("dataKey", "XH3");
//   * headInfoList.add(itemMap);
//   *
//   * List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
//   * Map<String, Object> dataItem = null; for(int i=0; i < 100; i++){ dataItem
//   * = new HashMap<String, Object>(); dataItem.put("XH1", "data" + i);
//   * dataItem.put("XH2", 88888888f); dataItem.put("XH3", "脉兜V5..");
//   * dataList.add(dataItem); } POIUtil.exportExcel2FilePath("test sheet 1"
//   * ,"F:\\temp\\customer2.xls", headInfoList, dataList);
//   *
//   * @param sheetName
//   *            sheet名称
//   * @param filePath
//   *            文件存储路径， 如：f:/a.xls
//   * @param headInfoList
//   *            List<Map<String, Object>> key: title 列标题 columnWidth 列宽
//   *            dataKey 列对应的 dataList item key
//   * @param dataList
//   *            List<Map<String, Object>> 导出的数据
//   * @throws java.io.IOException
//   *
//   */
//  public static void exportExcel2FilePath(String sheetName, String filePath,
//      List<Map<String, Object>> headInfoList, List<Map<String, Object>> dataList)
//      throws IOException {
////        ExcelUtil poiUtil = new ExcelUtil();
////        // 1.创建 Workbook
////        HSSFWorkbook hssfWorkbook = poiUtil.getHSSFWorkbook();
////        // 2.创建 Sheet
////        HSSFSheet hssfSheet = poiUtil.getHSSFSheet(hssfWorkbook, sheetName);
////        // 3.写入 head
////       // poiUtil.writeHeader(hssfWorkbook, hssfSheet, headInfoList);
////        // 4.写入内容
////       // poiUtil.writeContent(hssfSheet, 1, headInfoList, dataList);
////        // 5.保存文件到filePath中
////        poiUtil.write2FilePath(hssfWorkbook, filePath);
//  }
//
//  /**
//   * 根据坐标合并单元格 设置单元格样式 居中
//   * @param hssfWorkbook
//   * @param hssfSheet
//   * @param spanRows 记录的跨行数
//   * @param endColumn 需要参与跨行的最后一列
//   */
//  @SuppressWarnings({"unchecked", "rawtypes"})
//  public void mergeRows(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet, List spanRows,
//      int endColumn) {
//    HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
//    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//    Map<Integer, Integer> positions = transPositionMap(spanRows, 1);
//    for (int i = 0; i < endColumn; i++) {
//      for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
//        CellRangeAddress cra = new CellRangeAddress(entry.getKey(), entry.getValue(), i, i);
//        hssfSheet.addMergedRegion(cra);
//      }
//    }
//    for (int i = 1; i <= hssfSheet.getLastRowNum(); i++) {
//      HSSFRow row = hssfSheet.getRow(i);
//      for (int j = 0; j < endColumn; j++) {
//        HSSFCell cell = row.getCell(j);
//        cell.setCellStyle(cellStyle);
//      }
//    }
//  }
//
//  /**
//   * 跨行计算坐标点
//   *
//   * @param list 跨行的数量集合
//   * @param minRow 从哪一行开始跨行
//   */
//  private Map<Integer, Integer> transPositionMap(List<Integer> list, Integer minRow) {
//    if (CollectionUtils.isNotEmpty(list)) {
//      Map<Integer, Integer> linkedMap = new LinkedHashMap<>();
//      List<Integer> xPositionList = new ArrayList<>();
//      xPositionList.add(minRow);
//      for (Integer pos : list) {
//        if (pos == 1 || pos == 0) {
//          pos = 1;
//          Integer xPosition = Collections.max(xPositionList);
//          xPositionList.add(xPosition + pos);
//          continue;
//        }
//        Integer xPosition = Collections.max(xPositionList);
//        linkedMap.put(xPosition, xPosition + pos - 1);
//        xPositionList.add(xPosition + pos);
//      }
//      return linkedMap;
//    }
//    return Collections.emptyMap();
//  }
//
}
