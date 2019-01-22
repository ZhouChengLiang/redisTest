package org.zcl.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;

public class FileTypeConvertUtil {

    /**
     * 将HTML转成PD格式的文件。html文件的格式比较严格
     * @param htmlFile
     * @param pdfFile
     * @throws Exception
     */
    // <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd ">
    public static void html2pdf(String htmlFile, String pdfFile) throws Exception {
        // step 1
        String url = new File(htmlFile).toURI().toURL().toString();
        System.out.println(url);
        // step 2
        OutputStream os = new FileOutputStream(pdfFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        // step 3 解决中文支持
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if("linux".equals(getCurrentOperatingSystem())){
            //fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }else{// BaseFont.NOT_EMBEDDED
            fontResolver.addFont("/Users/zhoucl/Desktop/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        renderer.layout();
        renderer.createPDF(os);
        os.close();
        System.out.println("create pdf done!!");
    }
    public static void htmlStr2pdf(String content, String pdfFile) throws Exception {
    	// step 2
    	OutputStream os = new FileOutputStream(pdfFile);
    	ITextRenderer renderer = new ITextRenderer();
    	renderer.setDocumentFromString(content);
    	// step 3 解决中文支持
    	ITextFontResolver fontResolver = renderer.getFontResolver();
    	if("linux".equals(getCurrentOperatingSystem())){
    		//fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    	}else{
    		//fontResolver.addFont("c:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
    	}
    	fontResolver.addFont("/Users/zhoucl/Desktop/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
    	renderer.layout();
    	renderer.createPDF(os);
    	os.close();
    	System.out.println("htmlStr2pdf create pdf done!!");
    }

    public static String getCurrentOperatingSystem(){
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("---------当前操作系统是-----------" + os);
        return os;
    }
    
    public static String generateTable0() {
    	 StringBuilder sb = new StringBuilder();
    	 //sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd \">");
    	 sb.append("<html>");
         sb.append("<body style = \"font-family: SimSun;\">");
         sb.append("<center>");
         sb.append("<table width = \"20%\" border=\"1\">");
         
         sb.append("<tr>");
         sb.append("<th colspan=\"3\">就是测试一下</th>");//<!-- colspan="2"  占位,表示这一列占2列 -->
         sb.append("</tr>");
         
         sb.append("<tr>");
         sb.append("<th>网站</th>");
         sb.append("<th colspan=\"2\">统计情况</th>");//<!-- colspan="2"  占位,表示这一列占2列 -->
         sb.append("</tr>");
         
         sb.append("<tr>");
         sb.append("<td rowspan=\"3\">奇虎360</td>");// <!-- rowspan="2"  占位,表示这一列占3行 -->
         sb.append("<td>http://hao.360.cn/</td>");
         sb.append("<td>11</td>");
         sb.append("</tr>");
         
         sb.append("<tr>");
         sb.append("<td>http://sh.qihoo.com/</td>");
         sb.append("<td>22</td>");
         sb.append("</tr>");
         
         sb.append("<tr>");
         sb.append("<td>http://video.so.com/</td>");
         sb.append("<td>33</td>");
         sb.append("</tr>");
         
         sb.append("</table>");
         sb.append("</center>");
         sb.append("</body>");
         sb.append("</html>");
         return sb.toString();
    }
    
    /**
     * 模拟出货单的表格
     * @return
     */
    public static String generateTable1() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd \">");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<style type=\"text/css\">");
    	sb.append("table{border-collapse:collapse;}table, td, th{border:1px solid black;}");
    	sb.append("</style>");
    	sb.append("</head>");
    	
        sb.append("<body style = \"font-family: SimSun;font-size:8px\">");
        sb.append("<table align=\"center\">");
        //  第一行出货单  跨行数  为  1 + 1+1+ 所有的品项数 （4）
        sb.append("<tr>");
        sb.append("<td colspan=\"7\">出货单</td>");//<!-- colspan="7"  占位,表示这一列占7列 -->
        sb.append("</tr>");
        
        //第二行 补货员信息  创建时间
        sb.append("<tr>");
        sb.append("<td align=\"left\" colspan=\"7\">补货员：张三    创建时间：2018-12-27  10:32:25</td>");//<!-- colspan="7"  占位,表示这一列占7列 -->
        sb.append("</tr>");
        
        //第三行 计划带货数 跨行数  1 + 机器数 （任务下 网点下所有的机器数）3
        sb.append("<tr>");
        sb.append("<td rowspan=\"4\">计算带货数</td>");// <!-- rowspan="2"  占位,表示这一列占3行 -->
        sb.append("<td>网点</td>");
        sb.append("<td>机器</td>");
        // 再 所有的品项数 4
        sb.append("<td>NFC果汁-100%NFC橙汁-300ml*6瓶</td>");
        sb.append("<td>东方树叶茉莉花茶-500ml*6瓶</td>");
        sb.append("<td>力量帝维他命水柠檬风味-500ml*6瓶</td>");
        sb.append("<td>尖叫功能饮料纤维型-550ml*6瓶</td>");
        sb.append("</tr>");
        
        // 第四行 开始遍历网点 及 网点的机器数 对应的品项带货数  跨行数为对应的网点机器数
        //第一个网点 第一个机器
        sb.append("<tr>");
        sb.append("<td rowspan=\"2\">越秀公园</td>");
        //某一台机器
        sb.append("<td>1期2栋架空层</td>");
        // 对应的品项数量
        sb.append("<td>1</td>");
        sb.append("<td>3</td>");
        sb.append("<td>2</td>");
        sb.append("<td>0</td>");
        sb.append("</tr>");
        //第一个网点第二个机器
        sb.append("<tr>");
        //某一台机器
        sb.append("<td>3期架空层</td>");
        // 对应的品项数量
        sb.append("<td>4</td>");
        sb.append("<td>0</td>");
        sb.append("<td>4</td>");
        sb.append("<td>0</td>");
        sb.append("</tr>");
        
        //第二个网点 第一台机器
        sb.append("<tr>");
        sb.append("<td rowspan=\"1\">西溪印象城</td>");
        //某一台机器
        sb.append("<td>1楼电梯口</td>");
        // 对应的品项数量
        sb.append("<td>2</td>");
        sb.append("<td>1</td>");
        sb.append("<td>3</td>");
        sb.append("<td>1</td>");
        sb.append("</tr>");
        
        // 实际带货数合计 行
        sb.append("<tr>");
        sb.append("<td colspan=\"3\">实际带货数合计</td>");
        //对应到品项ID
        sb.append("<td>5</td>");
        sb.append("<td>7</td>");
        sb.append("<td>10</td>");
        sb.append("<td>5</td>");
        sb.append("</tr>");
        
    	sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
    	return sb.toString();
    }

    public static void main(String[] args) {
//    	String context = generateTable0();
        String context = generateTable1();
        String pdfFile = "/Users/zhoucl/Desktop/出货单.pdf";
        try {
            FileTypeConvertUtil.htmlStr2pdf(context, pdfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
