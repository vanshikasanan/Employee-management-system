package com.vanshika.employeesystemapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vanshika.employeesystemapi.entity.EmployeeEntity;
import com.vanshika.employeesystemapi.model.EmployeeModel;
import com.vanshika.employeesystemapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public EmployeeModel createEmployee(EmployeeModel employee) {
		
		EmployeeEntity employeeEntity=new EmployeeEntity();
		
		BeanUtils.copyProperties(employee, employeeEntity);
		employeeRepository.save(employeeEntity);
		
		return employee;
	}

	
	@Override
	public List<EmployeeModel> getAllEmployees() {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
		List <EmployeeModel> employee = employeeEntities.stream().map(emp -> new EmployeeModel(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail())).collect(Collectors.toList());
		return employee;
	}
	
	@Override
	public boolean deleteEmployee(Long id) {
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		employeeRepository.delete(employeeEntity);
		
		return true;
	}

	@Override
	public EmployeeModel getEmployeeById(Long id) {
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		EmployeeModel employee = new EmployeeModel();
		BeanUtils.copyProperties(employeeEntity, employee);
		return employee;
	}

	@Override
	public EmployeeModel updateEmployee(Long id, EmployeeModel employee) {
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		employeeEntity.setEmail(employee.getEmail());
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
		employeeRepository.save(employeeEntity);
		return employee;
	}

}
