package com.app.mvcappl.model;
public class DisplayModel {

	private Long empId;

	private String empName;

	private String empMailId;

	private String empJoiningDate;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpMailId() {
		return empMailId;
	}

	public void setEmpMailId(String empMailId) {
		this.empMailId = empMailId;
	}

	public String getEmpJoiningDate() {
		return empJoiningDate;
	}

	public void setEmpJoiningDate(String empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}

	@Override
	public String toString() {
		return "DisplayModel [empId=" + empId + ", empName=" + empName + ", empMailId=" + empMailId
				+ ", empJoiningDate=" + empJoiningDate + "]";
	}

}
