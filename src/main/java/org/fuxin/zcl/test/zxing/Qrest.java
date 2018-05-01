package org.fuxin.zcl.test.zxing;

import java.io.IOException;

import com.google.zxing.WriterException;

public class Qrest {
    public static void main(String[] args) {

     String content="大家好，我是吃瓜群众，很高兴认识大家";
     String logUri="d:/zcl.jpg";
     String outFileUri="d:/format.jpg";
     int[]  size=new int[]{430,430};
     String format = "jpg";  
     
       try {
        new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri,size);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (WriterException e) {
        e.printStackTrace();
    }   
  }

}
