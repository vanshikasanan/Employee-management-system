package com.vanshika.employeesystemapi.services;

import java.util.List;

import com.vanshika.employeesystemapi.model.EmployeeModel;

public interface EmployeeService {

	EmployeeModel createEmployee(EmployeeModel employee);

	List<EmployeeModel> getAllEmployees();

	boolean deleteEmployee(Long id);

	EmployeeModel getEmployeeById(Long id);

	EmployeeModel updateEmployee(Long id, EmployeeModel employee);

}
