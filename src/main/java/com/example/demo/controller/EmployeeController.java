package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
    private EmployeeRepository employeeRepository;

    // Create Employee
    @PostMapping("/add-employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employee
    @GetMapping("/get-all-employee")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    @GetMapping("/get-employee-by-id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    	Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        return ResponseEntity.ok(employee);
    }

    // Update employee
    @PutMapping("/update-employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
    	Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    	employee.setName(employeeDetails.getName());
    	employee.setEmail(employeeDetails.getEmail());
    	employee.setMobile(employeeDetails.getEmail());
    	employee.setAddress(employeeDetails.getAddress());
    	employee.setPosition(employeeDetails.getPosition());
    	employee.setStatus(employeeDetails.getStatus());

        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete employee
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    	Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
        return ResponseEntity.noContent().build();
    }
}
