package org.zcl.mytest.poiExcel;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyNetMachineVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7595205493208831805L;

	private String netName;
	
	private String machineName;
	
	private List<Integer> planNum;

}
