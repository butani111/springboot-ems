package com.example.ems.service;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.dto.EmployeeResponse;
import com.example.ems.entity.Employee;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size) {
//        return repository.findAll(PageRequest.of(page, size, Sort.by("salary").descending()));
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Employee getEmployee(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest req) {
        Employee employee = new Employee();
        employee.setName(req.getName());
        employee.setEmail(req.getEmail());
        employee.setDepartment(req.getDepartment());
        employee.setSalary(req.getSalary());

        repository.save(employee);

        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
    }

    @Override
    public Employee updateEmployee(Long id, Employee updated) {
        Employee employee = repository.findById(id).orElseThrow();
        employee.setName(updated.getName());
        employee.setDepartment(updated.getDepartment());
        employee.setSalary(updated.getSalary());
        return repository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
