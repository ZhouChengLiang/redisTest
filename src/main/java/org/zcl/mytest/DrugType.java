package org.zcl.mytest;

import java.util.List;

public class DrugType {
	
	private String code;
	
	private String name;
	
	private Integer type;
	
	private List<DrugType> subDrugType;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<DrugType> getSubDrugType() {
		return subDrugType;
	}

	public void setSubDrugType(List<DrugType> subDrugType) {
		this.subDrugType = subDrugType;
	}
	
	
	
}
