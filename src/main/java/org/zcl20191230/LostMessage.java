package org.zcl20191230;

/**
 * 
 * @author zhoucl
 *
 */
public class LostMessage {
    
    void f() throws VeryImportantException{
        throw new VeryImportantException();
    }
    
    void dispose() throws HoHumException{
        throw new HoHumException();
    }
    
    public static void main(String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            }catch(Exception e) {
                System.out.println(e);
            } finally {
                lm.dispose();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
class VeryImportantException extends Exception{
    /**
     * 
     */
    private static final long serialVersionUID = 6447064420293115990L;

    public String toString() {
        return "A very important exception!";
    }
}
class HoHumException extends Exception{
    /**
     * 
     */
    private static final long serialVersionUID = -205200260523562844L;

    public String toString() {
        return "A trivial exception";
    }
}