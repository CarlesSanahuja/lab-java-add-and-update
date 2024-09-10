package com.hospital.service;

import com.hospital.enums.Department;
import com.hospital.enums.Status;
import com.hospital.model.Employee;
import com.hospital.model.Patient;
import com.hospital.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getEmployeesByStatus(Status status) {
        return employeeRepository.findByStatus(status);
    }

    public List<Employee> getEmployeesByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }
    public Employee createDoctor(Employee Employee){
        return employeeRepository.save(Employee);
    }

    public Optional<Employee> updateDoctorStatus(Long id, Status newStatus) {
        return employeeRepository.findById(id).map(existingDoctor -> {
            existingDoctor.setStatus(newStatus);  // Actualizar solo el status
            return employeeRepository.save(existingDoctor);
        });
    }


    public Optional<Employee> updateDoctorDepartment(Long id, Department newDepartment) {
        return employeeRepository.findById(id).map(existingDoctor -> {
            existingDoctor.setDepartment(newDepartment);  // Solo actualizamos el departamento
            return employeeRepository.save(existingDoctor);
        });
    }

}
