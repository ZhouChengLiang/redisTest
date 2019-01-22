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
public class ExtralContentVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3559623532999579816L;

	private String content;
	
	private Boolean center;
}
