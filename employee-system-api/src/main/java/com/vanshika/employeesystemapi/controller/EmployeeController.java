package com.vanshika.employeesystemapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanshika.employeesystemapi.model.EmployeeModel;
import com.vanshika.employeesystemapi.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService, EmployeeService employeeService2) {
		this.employeeService = employeeService;
		
	}
	
	@PostMapping("/employees")
	public EmployeeModel createEmployee(@RequestBody EmployeeModel employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/employees")
	public List<EmployeeModel> getAllEmployees(){
		return employeeService.getAllEmployees();	
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		boolean deleted = false;
		deleted = employeeService.deleteEmployee(id);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",deleted);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id){
		EmployeeModel employee= null;
		employee= employeeService.getEmployeeById(id);
		
		return ResponseEntity.ok(employee); 
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel employee){
		employee=  employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(employee); 
	}
}
