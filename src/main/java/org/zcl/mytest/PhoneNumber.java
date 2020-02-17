package org.zcl.mytest;

public class PhoneNumber {
    
    private Integer areaCode;
    
    private Integer prefix;
    
    private Integer lineNumer;
    
    public PhoneNumber() {
        
    }
    
    public PhoneNumber(Integer areaCode, Integer prefix, Integer lineNumer) {
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
        PhoneNumber other = (PhoneNumber) obj;
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
    
}
