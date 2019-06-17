package com.emp.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emp.crud.model.Employee;

@Service
public interface EmployeeService {

	public List<Employee> getEmpList();

	public Employee getEmpById(long id);

	public String updateEmpDetails(Employee emp);

	public String deleteEmpById(long id);

	public String createEmp(Employee emp);
}
