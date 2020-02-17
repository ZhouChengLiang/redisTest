package org.zcl.mytest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeInfo {
    private Integer id;

    private Integer grade;

    private Integer requiredExperience;
    
    private Integer totalSumExperience;

    private String gradeName;

    private String gradeIcon;

    private Integer currentUsers;

    private Integer countyCode;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GradeInfo other = (GradeInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getRequiredExperience() {
		return requiredExperience;
	}

	public void setRequiredExperience(Integer requiredExperience) {
		this.requiredExperience = requiredExperience;
	}

	public Integer getTotalSumExperience() {
		return totalSumExperience;
	}

	public void setTotalSumExperience(Integer totalSumExperience) {
		this.totalSumExperience = totalSumExperience;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeIcon() {
		return gradeIcon;
	}

	public void setGradeIcon(String gradeIcon) {
		this.gradeIcon = gradeIcon;
	}

	public Integer getCurrentUsers() {
		return currentUsers;
	}

	public void setCurrentUsers(Integer currentUsers) {
		this.currentUsers = currentUsers;
	}

	public Integer getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}

}