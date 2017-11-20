package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserExpInt {
    private Integer userId;

    private Integer experience;

    private Integer integral;
    
    private Integer countyCode;

	@Override
	public String toString() {
		return "user [" + userId + "," + countyCode + "]";
	}
    
    

}