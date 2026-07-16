package com.example.ems.service;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.dto.EmployeeResponse;
import com.example.ems.entity.Employee;
import com.example.ems.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest req) {
        Employee employee = new Employee();
        employee.setId(req.getId());
        employee.setName(req.getName());
        employee.setEmail(req.getEmail());
        employee.setDepartment(req.getDepartment());
        employee.setSalary(req.getSalary());

        employees.add(employee);

        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        for (int i = 0; i < employees.size(); ++i) {
            if (employees.get(i).getId().equals(id)) {
                employees.set(i, employee);
                return employees.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }
}
