package org.zcl.mytest;

import java.util.Comparator;

public class PhoneNumber2 implements Comparable<PhoneNumber2>{
    
    private static final Comparator<PhoneNumber2> COMPARATOR0 = Comparator.comparingInt(( PhoneNumber2 pn) -> pn.getAreaCode());
    
    private static final Comparator<PhoneNumber2> COMPARATOR1 = Comparator.comparingInt((PhoneNumber2 pn) -> pn.getPrefix()).reversed();
    
    private static final Comparator<PhoneNumber2> COMPARATOR2 = Comparator.comparingInt(( PhoneNumber2 pn) -> pn.getLineNumer());
    
    private static final Comparator<PhoneNumber2> COMPARATOR3 = COMPARATOR0.thenComparing(COMPARATOR1).thenComparing(COMPARATOR2);
    
    private Integer areaCode;
    
    private Integer prefix;
    
    private Integer lineNumer;
    
    
    public PhoneNumber2() {
        
    }
    
    public PhoneNumber2(Integer areaCode, Integer prefix, Integer lineNumer) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumer = lineNumer;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getPrefix() {
        return prefix;
    }

    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    public Integer getLineNumer() {
        return lineNumer;
    }

    public void setLineNumer(Integer lineNumer) {
        this.lineNumer = lineNumer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((areaCode == null) ? 0 : areaCode.hashCode());
        result = prime * result + ((lineNumer == null) ? 0 : lineNumer.hashCode());
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhoneNumber2 other = (PhoneNumber2) obj;
        if (areaCode == null) {
            if (other.areaCode != null)
                return false;
        } else if (!areaCode.equals(other.areaCode))
            return false;
        if (lineNumer == null) {
            if (other.lineNumer != null)
                return false;
        } else if (!lineNumer.equals(other.lineNumer))
            return false;
        if (prefix == null) {
            if (other.prefix != null)
                return false;
        } else if (!prefix.equals(other.prefix))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PhoneNumber [areaCode=" + areaCode + ", prefix=" + prefix + ", lineNumer=" + lineNumer + "]";
    }

    @Override
    public int compareTo(PhoneNumber2 o) {
        return COMPARATOR3.compare(this, o);
    }
    
}
