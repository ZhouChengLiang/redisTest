package org.zcl20191230;

import java.util.Base64;
import java.util.Observable;

public class OilFutures extends Observable {
    
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        super.setChanged();
        super.notifyObservers(price);
        this.price = price;
    }
    
    public static void main(String[] args) {
        String abc = new String(Base64.getDecoder().decode("4oCtMTUwNjA3NTUyNjnigKw="));// MTUwNjA3NTUyNjnigKwJ
        System.out.println(abc);
        
        abc = new String(Base64.getEncoder().encode("‭‭‭‭‭15060755269‬".getBytes()));
        System.out.println(abc);
        
        abc = new String(Base64.getDecoder().decode("4oCt"));// MTUwNjA3NTUyNjnigKwJ
        System.out.println(abc);
        
//        abc = new String(Base64.getDecoder().decode("J"));// MTUwNjA3NTUyNjnigKwJ
//        System.out.println(abc);
    }
    
    
}
