package com.app.mvcappl.service;
import java.util.List;

import com.app.mvcappl.model.DisplayModel;
import com.app.mvcappl.model.RequestModel;


public interface IEmployeeService {

	DisplayModel addEmployee(RequestModel employeeEntity);
	
	List<DisplayModel> getEmpList();
	
	DisplayModel getEmpById(Long empId);
	
	DisplayModel updateEmp(Long empId,RequestModel employeeEntity);
	
	void deleteEmp(Long empId);
}