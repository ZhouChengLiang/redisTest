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
public class MyPlanVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7816638640826561038L;

	private Integer taskId;
	
	private Integer netId;
	
	private String netName;
	
	private String machineId;
	
	private String machineName;
	
	private Integer skuId;
	
	private Integer planBringOutNum;
	
	private String groupByStr;
	
}
