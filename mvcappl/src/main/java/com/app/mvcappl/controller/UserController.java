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
@RequestMapping("/employee")
public class UserController {

	@Autowired
	private IEmployeeService empService;

	@GetMapping("/signup")
	public String getEmployeeForm(RequestModel requestModel) {
		return "user/add-user";
	}

	@PostMapping("/add")
	public String addEmployee(@Valid RequestModel requestModel, BindingResult result) {
		if (result.hasErrors()) {
			return "user/add-user";
		}
		empService.addEmployee(requestModel);
		return "redirect:/employee/list";
	}

	@GetMapping("/list")
	public String getEmployeesList(Model model) {
		model.addAttribute("emps", empService.getEmpList());
		return "user/user-list";
	}

	@GetMapping("/edit/{empId}")
	public String showUpdateForm(@PathVariable("empId") Long empId, Model model) {
		DisplayModel exiEmp = empService.getEmpById(empId);
		model.addAttribute("emp", exiEmp);
		return "user/update-user";
	}

	@PostMapping("/update/{empId}")
	public String updateEmployee(@PathVariable("empId") Long empId, @Valid RequestModel requestModel,
			BindingResult result) {
		if (result.hasErrors()) {
			return "user/update-user";
		}
		empService.updateEmp(empId, requestModel);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/delete/{empId}")
	public String deleteEmployee(@PathVariable("empId") Long empId) {
		empService.deleteEmp(empId);
		return "redirect:/employee/list";
	}
}
