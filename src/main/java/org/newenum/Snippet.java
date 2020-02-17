package org.newenum;

public class Snippet {
    public static String frontCompWithZore(int sourceData, int formatLength) {
        return String.format("%0" + formatLength + "d", sourceData);
    }
    
    public static void main(String[] args) {
        System.out.println(frontCompWithZore(1, 2));
    }
}
