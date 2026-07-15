package com.example.ems.service;

import com.example.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees ();

    Employee getEmployee (Long id);

    Employee createEmployee (Employee employee);

    Employee updateEmployee (Long id, Employee employee);

    void deleteEmployee (Long id);
}
