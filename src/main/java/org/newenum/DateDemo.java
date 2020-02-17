package org.newenum;

import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        Date d1 = new Date();
        long time1 = d1.getTime();
        System.out.println(time1 * 1.0/(1000 * 60 * 60 * 24 * 365L)+"年");
        System.out.println(time1 * 1.0/(1000 * 60 * 60 * 24 )/365+"年");
        System.out.println(time1 * 1.0/1000 / 60 / 60 / 24 / 365+"年");
    }
}
