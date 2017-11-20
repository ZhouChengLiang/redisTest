package org.zcl.mytest;

public class Comments {
	private Integer id;
	
	private Integer auditUserId;

    private String auditUserTruename;
    
    private Integer countyCode;
    
    private String  countyName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserTruename() {
		return auditUserTruename;
	}

	public void setAuditUserTruename(String auditUserTruename) {
		this.auditUserTruename = auditUserTruename;
	}

	public Integer getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(Integer countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", auditUserId=" + auditUserId + ", auditUserTruename=" + auditUserTruename
				+ ", countyCode=" + countyCode + ", countyName=" + countyName + "]";
	}
    
}
