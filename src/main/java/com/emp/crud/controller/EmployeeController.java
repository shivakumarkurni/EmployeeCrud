package com.emp.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.crud.model.Employee;
import com.emp.crud.service.EmployeeService;

@RestController
@RequestMapping("/rest")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employee")
	public List<Employee> getList() {
		return employeeService.getEmpList();

	}

	@PostMapping("/createEmployee")
	public String createEmployee(@RequestBody Employee employee) {

		return employeeService.createEmp(employee);
	}
	
	@GetMapping("/employeeById/{id}")
	public Employee getEmployeeById(@PathVariable(name="id") long id) {
		
		return employeeService.getEmpById(id);
		
	}
	
	@PutMapping("/updateEmployee")
	public String updateEmpDetails(@RequestBody Employee emp) {
		return employeeService.updateEmpDetails(emp);
	}
	
	@DeleteMapping("/deleteEmployeeById{id}")
	public String deleteEmpById(@PathVariable long id) {
		
		return employeeService.deleteEmpById(id);
	}
	
	
}
