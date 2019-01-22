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
public class MyRealVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8400833045588416312L;

	private Integer taskId;
	
	private Integer skuId;
	
	private Integer realBringOutNum;
}
