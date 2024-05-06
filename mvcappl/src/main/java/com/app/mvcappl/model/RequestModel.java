package com.app.mvcappl.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestModel {

	private Long empId;

	@NotNull(message = "Employee Name required!!!")
	@NotBlank(message = "Employee Name cannot be blank.")
	private String empName;

	@NotNull(message = "Employee Mail-id required!!!")
	@NotBlank(message = "Employee Mail-id cannot be blank.")
	private String empMailId;

	@NotNull(message = "Employee JoiningDate required!!!")
	private Date empJoiningDate;

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

	public Date getEmpJoiningDate() {
		return empJoiningDate;
	}

	public void setEmpJoiningDate(String empJoiningDate) {
		SimpleDateFormat formattor = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parsedDate = formattor.parse(empJoiningDate);
			this.empJoiningDate = parsedDate;
		} catch (ParseException e) {
			System.out.println("Exception durnig parsinf date : "+e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "responseModel [empId=" + empId + ", empName=" + empName + ", empMailId=" + empMailId
				+ ", empJoiningDate=" + empJoiningDate + "]";
	}

}
