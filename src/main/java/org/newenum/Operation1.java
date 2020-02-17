package org.newenum;

import java.util.function.DoubleBinaryOperator;

import lombok.Getter;

@Getter
public enum Operation1 {
    
    PLUS("+",(x,y)->x+y),
    MINUS("-",(x,y)->x-y),
    TIMES("*",(x,y)->x*y),
    DIVIDE("/",(x,y)->x/y),
    ;
    
    private final String symbol;
    
    private final DoubleBinaryOperator op;
    
    Operation1(String symbol,DoubleBinaryOperator op){
       this.symbol = symbol;
       this.op = op;
    }
    
    public double apply(double x,double y) {
       return op.applyAsDouble(x, y); 
    }
    
    public static void main(String[] args) {
        System.out.println(Operation1.PLUS.apply(1, 3));
        System.out.println(Operation1.MINUS.apply(1, 3));
        System.out.println(Operation1.TIMES.apply(1, 3));
        System.out.println(Operation1.DIVIDE.apply(1, 3));
    }
}
