package com.hospital.controller;

import com.hospital.enums.Department;
import com.hospital.enums.Status;
import com.hospital.model.Employee;
import com.hospital.model.Patient;
import com.hospital.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Employee>> getDoctorsByStatus(@PathVariable Status status){
        List<Employee> employees = employeeService.getEmployeesByStatus(status);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Department department){
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public Employee createDoctor(@RequestBody Employee employee){
        return employeeService.createDoctor(employee);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Employee> updateDoctorStatus(@PathVariable Long id, @RequestBody String newStatus) {
        Status status = Status.valueOf(newStatus.toUpperCase()); // Convertir el string a ENUM
        Optional<Employee> updatedDoctor = employeeService.updateDoctorStatus(id, status);
        return updatedDoctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}/department")
    public ResponseEntity<Employee> updateDoctorDepartment(@PathVariable Long id, @RequestBody Department newDepartment) {
        Optional<Employee> updatedDoctor = employeeService.updateDoctorDepartment(id, newDepartment);
        return updatedDoctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
