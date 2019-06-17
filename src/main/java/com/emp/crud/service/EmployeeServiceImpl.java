package com.emp.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.crud.model.Employee;
import com.emp.crud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getEmpList() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmpById(long id) {
		return employeeRepository.findByEmpId(id).get();
		
	}

	@Override
	public String updateEmpDetails(Employee emp) {
		
		if("Optional.empty".contains(""+employeeRepository.findById(emp.getEmpId()))){
			return "Employee with id "+emp.getEmpId()+" Doesn't exist";
		}else {
			Employee updatedEmp = employeeRepository.findById(emp.getEmpId()).get();
			
			if(emp.getEmpAddress() != null && !emp.getEmpAddress().isEmpty())
				updatedEmp.setEmpAddress(emp.getEmpAddress());
			if(emp.getEmpContact()!=null && !emp.getEmpContact().isEmpty())
				updatedEmp.setEmpContact(emp.getEmpContact());
			if(emp.getEmpEmail()!= null && !emp.getEmpEmail().isEmpty())
				updatedEmp.setEmpEmail(emp.getEmpEmail());
			if(emp.getEmpName()!= null && !emp.getEmpName().isEmpty())
				updatedEmp.setEmpName(emp.getEmpName());
			
			if(employeeRepository.save(updatedEmp) != null)
				return "employee details updated successfully";
			else 
				return "There is some error internally !! Plz try agiain later";
		}
		
		
	}

	@Override
	public String deleteEmpById(long id) {

		if("Optional.empty".contains(""+employeeRepository.findById(id))){
			return "Employee with id "+id+" Doesn't exist";
		}else {
			employeeRepository.deleteById(id);
			return "Employee deleted successfully";
		}
			
	}

	@Override
	public String createEmp(Employee emp) {
		
		if(employeeRepository.save(emp)!=null) {
			return "Employee Created";
		}
		else
			return "Some thing went wrong Plz try again";
	}

}
