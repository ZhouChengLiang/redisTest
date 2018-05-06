package org.fuxin.zcl.test.watermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class JunitImage2 {
	
	
    @Test
    public void testImage(){
//        markImageByText("知药管家","D:/zcl.jpg","D:/zcl_xel.jpg",null,Color.RED,"JPG");
        markImageByText("知药管家","D:/zcl.jpg","D:/zcl_xel.jpg",30,Color.YELLOW,"JPG");
    }
 
    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     * @param logoText 要写入的文字
     * @param srcImgPath 源图片路径
     * @param newImagePath 新图片路径
     * @param degree 旋转角度
     * @param color  字体颜色
     * @param formaName 图片后缀
     */
    public static void markImageByText(String logoText, String srcImgPath,String newImagePath,Integer degree,Color color,String formaName) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            java.awt.Image srcImg = ImageIO.read(new File(srcImgPath));
            int width = srcImg.getWidth(null);
            int heigth = srcImg.getHeight(null);
            BufferedImage buffImg = new BufferedImage(width,heigth, BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            g.drawImage(srcImg.getScaledInstance(width, heigth, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),  buffImg.getWidth()/2,buffImg.getHeight() /2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(new java.awt.Font("微软雅黑", java.awt.Font.BOLD, 30));
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3F));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            int width1 = 30*getTextLength(logoText);
            int heigth1 = 30;
            int x = -width/2;
            int y = -heigth/2;
            while(x<width*1.5){
            	y = -heigth/2;
            	while(y<heigth*1.5){
            		g.drawString(logoText, x, y);
            		y +=heigth1+100;
            	}
            	x+= width1 + 100;
            }
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(newImagePath);
            ImageIO.write(buffImg, formaName, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static int getTextLength(String text){
    	int length = text.length();
    	for(int i = 0;i<text.length();i++){
    		String s = String.valueOf(text.charAt(i));
    		if(s.getBytes().length>1){
    			length++;
    		}
    	}
    	
    	return length%2==0?length/2:length/2+1;
    }
 
}