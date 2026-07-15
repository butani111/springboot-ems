package com.example.ems.service;

import com.example.ems.entity.Employee;
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
                .orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employees.add(employee);
        return employee;
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
