package org.fuxin.zcl.test.zxing;

public class Qrest {
    public static void main(String[] args) {

//     String content="大家好，我是吃瓜群众，很高兴认识大家";
//     String logUri="d:/zcl.jpg";
//     String outFileUri="d:/format.jpg";
//     int[]  size=new int[]{430,430};
//     String format = "jpg";  
//     
//       try {
//        new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri,size);
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (WriterException e) {
//        e.printStackTrace();
//    }   
    	int a = 1;
    	for(int i = 0;i<6;i++) {
    		System.out.println("i >>>> "+i);
    		for(int j = i;j<=i+1;j++) {
    			System.out.println(i+" 行 j >>>> "+j);
    			if(j == 3) {
    				a = j;
    				break;
    			}
    		}
    	}
    	System.out.println("a >>> "+a);
    	System.out.println("###########################################");
    	int b = 1;
    	loop:
    	for(int i = 0;i<6;i++) {
    		System.out.println("i >>>> "+i);
    		for(int j = i;j<=i+1;j++) {
    			System.out.println(i+" 行 j >>>> "+j);
    			if(j == 3) {
    				b = j;
    				break loop;
    			}
    		}
    	}
    	System.out.println("b >>> "+b);
  }

}
