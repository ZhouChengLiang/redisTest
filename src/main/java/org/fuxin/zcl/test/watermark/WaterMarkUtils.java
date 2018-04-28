package org.fuxin.zcl.test.watermark;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WaterMarkUtils {

    /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent,Color markContentColor,Font font) {

        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
//            int x = srcImgWidth - getWatermarkLength(waterMarkContent, g);  
            int x = 0;  
            int y = srcImgHeight;  
            
            System.out.println("x--->"+x+", y--->"+y+", srcImgWidth--->"+srcImgWidth+", --->"+srcImgHeight);
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();  
            // 输出图片  
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");  
            outImgStream.flush();  
            outImgStream.close();  
        } catch (Exception e) {
        }
    }
    
    public byte[] outputByteArray(String srcImgPath, String tarImgPath, String waterMarkContent,Color markContentColor,Font font) {
    	try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 2)) {
    		// 读取原图片信息
    		File srcImgFile = new File(srcImgPath);//得到文件
    		Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
    		int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
    		int srcImgHeight = srcImg.getHeight(null);//获取图片的高
    		// 加水印
    		BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
    		Graphics2D g = bufImg.createGraphics();
    		g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
    		g.setColor(markContentColor); //根据图片的背景设置水印颜色
    		g.setFont(font);              //设置字体
    		int x = 0;  
    		int y = srcImgHeight;  
    		System.out.println("x--->"+x+", y--->"+y+", srcImgWidth--->"+srcImgWidth+", --->"+srcImgHeight);
    		g.drawString(waterMarkContent, x, y);  //画出水印
    		g.dispose();  
    		ImageIO.write(bufImg, "jpg", byteArrayOutputStream);
    		return byteArrayOutputStream.toByteArray();
    	} catch (IOException e) {
			e.printStackTrace();
		} 
    	return new byte[0];
    }
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());  
    }  
    
    public static void main(String[] args) {
        Font font = new Font("微软雅黑", Font.PLAIN, 35);                     //水印字体
        String srcImgPath="d:/zcl.jpg"; //源图片地址
        String tarImgPath="d:/zcl_xel.jpg"; //待存储的地址
        String waterMarkContent="知药管家";  //水印内容
        Color color=new Color(255,255,255,128);                               //水印图片色彩以及透明度
        //new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath,waterMarkContent,color,font);
        
        byte[] buff = new WaterMarkUtils().outputByteArray(srcImgPath, tarImgPath, waterMarkContent, color, font);
        System.out.println(buff.length);
    }
}