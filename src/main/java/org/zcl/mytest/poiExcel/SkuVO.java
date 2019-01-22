package org.zcl.mytest.poiExcel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkuVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1955899124442981591L;
	
	private Integer skuId;
	
	private String skuName;
	
	private String specification;

}
