package org.zcl20191230;

public class AboutThrowException implements ChildMaster{

    @Override
    public void abc() throws MyException1{
        
    }

}
interface ChildMaster{
    void abc() throws MyException1;
}

class MyException1 extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = -168322892958856746L;
    
}
class MyException2 extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = -7677806921970403445L;
    
    
}