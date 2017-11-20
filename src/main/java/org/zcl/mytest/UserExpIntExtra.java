package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserExpIntExtra {
    private Integer userId;

    private Integer experience;

    private Integer integral;
    
    private Integer countyCode;
    
    private GradeInfo gradeInfo;
    
    private Integer beyondOthers;
    
    private String doubleRank;
	@Override
	public String toString() {
		return "user [" + userId + "," + countyCode + "," + gradeInfo.getGrade()+"," + gradeInfo.getGradeName()+","+doubleRank+","+beyondOthers+"]";
	}
    
    

}