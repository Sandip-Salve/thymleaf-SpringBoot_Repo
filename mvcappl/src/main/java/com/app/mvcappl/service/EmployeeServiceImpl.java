package com.app.mvcappl.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mvcappl.dao.IEmployeeRepository;
import com.app.mvcappl.entity.EmployeeEntity;
import com.app.mvcappl.model.DisplayModel;
import com.app.mvcappl.model.RequestModel;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private IEmployeeRepository empRepo;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public DisplayModel addEmployee(RequestModel input) {
		DisplayModel response = null;
		if(input!=null) {
			EmployeeEntity emp = new EmployeeEntity();
			emp.setEmpName(input.getEmpName());
			emp.setEmpMailId(input.getEmpMailId());
			emp.setEmpJoiningDate(input.getEmpJoiningDate());
			EmployeeEntity savedUser = empRepo.save(emp);
			response = new DisplayModel();
			response.setEmpId(savedUser.getEmpId());
			response.setEmpName(savedUser.getEmpName());
			response.setEmpMailId(savedUser.getEmpMailId());
			response.setEmpJoiningDate(sdf.format(savedUser.getEmpJoiningDate()));
		}
		return response;
	}

	@Override
	public List<DisplayModel> getEmpList() {
		List<EmployeeEntity> empList = empRepo.findAll();
		List<DisplayModel> responseList = new ArrayList<>();
		for(EmployeeEntity emp : empList) {
			DisplayModel response = new DisplayModel();
			response.setEmpId(emp.getEmpId());
			response.setEmpName(emp.getEmpName());
			response.setEmpMailId(emp.getEmpMailId());
			response.setEmpJoiningDate(sdf.format(emp.getEmpJoiningDate()));
			responseList.add(response);
		}
		return responseList;
	}

	@Override
	public DisplayModel getEmpById(Long empId) {
		EmployeeEntity emp = empRepo.findById(empId).orElse(null);
		DisplayModel response = null;
		if(emp!=null) {
			response = new DisplayModel();
			response.setEmpId(emp.getEmpId());
			response.setEmpName(emp.getEmpName());
			response.setEmpMailId(emp.getEmpMailId());
			response.setEmpJoiningDate(sdf.format(emp.getEmpJoiningDate()));
		}
		return response;
	}

	@Override
	public DisplayModel updateEmp(Long empId, RequestModel employeeEntity) {
		EmployeeEntity emp = empRepo.findById(empId).orElse(null);
		if(emp!=null) {
			emp.setEmpName(employeeEntity.getEmpName());
			emp.setEmpMailId(employeeEntity.getEmpMailId());
			emp.setEmpJoiningDate(employeeEntity.getEmpJoiningDate());
			empRepo.save(emp);
		}
		DisplayModel response = new DisplayModel();
		response.setEmpId(emp.getEmpId());
		response.setEmpName(emp.getEmpName());
		response.setEmpMailId(emp.getEmpMailId());
		response.setEmpJoiningDate(sdf.format(emp.getEmpJoiningDate()));
		return response;
	}

	@Override
	public void deleteEmp(Long empId) {
		if(empId!=null) {
			empRepo.deleteById(empId);
		}
		
	}

}
