package com.app.mvcappl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.mvcappl.model.DisplayModel;
import com.app.mvcappl.model.RequestModel;
import com.app.mvcappl.service.IEmployeeService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private IEmployeeService empService;
	
	@GetMapping("/signup")
	public String showSignUpForm(RequestModel requestModel) {
		return "home/add-emp";
	}
	
	@PostMapping("/add")
	public String addEmployee(@Valid RequestModel requestModel, BindingResult result) {
		if(result.hasErrors()) {
			return "home/add-emp";
		}
		empService.addEmployee(requestModel);
		return "redirect:/home/list";
	}
	
	@GetMapping("/list")
	public String getEmpList(Model model) {
		model.addAttribute("emps",empService.getEmpList());
		return "home/emp-list";
	}
	
	@GetMapping("/edit/{empId}")
	public String viewEmployeeForm(@PathVariable("empId")Long empId, Model model) {
		DisplayModel empById = empService.getEmpById(empId);
		model.addAttribute("emp", empById);
		return "home/view-emp";
	}
	
	@GetMapping("/update/{empId}")
	public String viewUpdateForm(@PathVariable("empId") Long empId, Model model) {
		DisplayModel emp = empService.getEmpById(empId);
		String empJoiningDate = emp.getEmpJoiningDate();
		empJoiningDate = dateFormatter(empJoiningDate);
		emp.setEmpJoiningDate(empJoiningDate);
		model.addAttribute("emp", emp);
		return "home/update-emp";
	}
	
	private String dateFormatter(String empJoiningDate) {
		String[] arr = empJoiningDate.split("-");
		return arr[2]+"-"+arr[1]+"-"+arr[0];
	}

	@PostMapping("/updateEmp/{empId}")
	public String updateEmp(@PathVariable("empId")Long empId, @Valid RequestModel requestModel, BindingResult result) {
		if(result.hasErrors()) {
			return "home/update-emp";
		}
		empService.updateEmp(empId, requestModel);
		return "redirect:/home/list";
	}
	
	@GetMapping("/delete/{empId}")
	public String deleteEmp(@PathVariable("empId")Long empId) {
		empService.deleteEmp(empId);
		return "redirect:/home/list";
	}
}
