package com.example.ems.service;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.dto.EmployeeResponse;
import com.example.ems.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Page<Employee> getAllEmployees(int page, int size);

    Employee getEmployee(Long id);

    EmployeeResponse createEmployee(EmployeeRequest employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}
