package org.zcl20191230;

public class FinallyWorks {
    
    static int count = 0;
    
    public static void main(String[] args) {
        while(true) {
            try {
                if(count++ == 0) {
                   throw new ThreeException(); 
                }
                System.out.println("No exception");
            } catch (ThreeException e) {
                System.out.println("ThreeException");
            }finally {
               System.out.println("In finally clause"); 
               if(count == 2) {
                  break; 
               }
            }
        }
    }
}

class ThreeException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = -3744766105230554089L;
    
}