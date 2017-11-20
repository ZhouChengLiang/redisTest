package org.zcl.mytest.poiExcel;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.joda.time.DateTime;
/**
 * import org.apache.ibatis.ognl.Ognl;
   import org.apache.ibatis.ognl.OgnlContext;
 * @author Administrator
 *
 * @param <T>
 */

public abstract class AbstractExcelHandle<T> {

	private ExcelWriter writer;
	private String[] indexs;
	private List<T> records;
	private String title;
	private int currentRow=1;
	private OutputStream outputStream;

	public AbstractExcelHandle(){}

	public AbstractExcelHandle(String title, String indexs, final OutputStream outputStream) throws IOException {
		this.indexs = indexs.split(",");
		this.title=title;
		this.outputStream =outputStream;
		writer = new ExcelWriter();
		initExcel(writer,title);
	}

	public void handle(List<T> list) throws Exception{
		appendRows(list);
	}

	@Deprecated
	public void handle() throws Exception{
		initExcel(writer,title);
		appendRows(records);
		writer();
	}

	public void writer() throws IOException {
		OutputStream outputStream = new BufferedOutputStream(this.outputStream);
		writer.writeExcelFileData(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	private void initExcel(ExcelWriter writer, String title) {
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);

		writer.addWorkSheet(title);
		appendTitle(title);
		appendTableHead();
	}

	public void appendRows(List<T> records) {
		try {
			for (T t : records) {
				if(t != null){
					currentRow++;
					appendRow(currentRow, t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void appendTitle(String title) {
		writer.addCell(0, 0, title).mergeTo(0, indexs.length - 1)
				.setFont("宋体", 20, true, false, false, false).setHorizontalAlignment(
						ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead() {
		Map<String, String> titleMap = getTitleMap();
		for (int i = 0; i < indexs.length; i++) {
			writer.addCell(1, i, titleMap.get(indexs[i])).setFont("宋体", 12, true, false, false, false);
		}
	}

	protected abstract Map<String, String> getTitleMap();

	void appendRow(int index, T t) throws Exception {
		OgnlContext ognlContext = new OgnlContext();
		ognlContext.setRoot(t);
		for (int i = 0; i < indexs.length; i++) {
			if (StringUtils.isNotBlank(indexs[i])) {
				Object value = getValue(ognlContext, indexs[i]);
				if (value != null) {
					if (value instanceof Date) {
						writer.addCell(index, i, new DateTime((Date) value).toString("yyyy年MM月dd日 HH:mm:ss",
								Locale.CHINESE));
					} else {
						writer.addCell(index, i, value.toString());
					}
					continue;
				}
			}
			writer.addCell(index, i, "");
		}
	}

	protected Object getValue(OgnlContext ognlContext, String ognlExpression) throws Exception {
		if (ognlExpression.indexOf('.') != -1) {
			String[] expressions = ognlExpression.split("\\.");
			String exp = "";
			Object value = null;
			for (int i = 0; i < expressions.length; i++) {
				if (i == 0) {
					exp = expressions[i];
				} else {
					exp = exp + "." + expressions[i];
				}
				value = Ognl.getValue(Ognl.parseExpression(exp), ognlContext, ognlContext.getRoot());
				if (null == value) {
					break;
				}
			}
			return value;
		} else {
			return Ognl.getValue(Ognl.parseExpression(ognlExpression), ognlContext, ognlContext.getRoot());
		}
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

}