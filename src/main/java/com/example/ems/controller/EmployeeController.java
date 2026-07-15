package com.example.ems.controller;

import com.example.ems.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>(
            List.of(
                    new Employee(1L, "Alice", "HR"),
                    new Employee(2L, "Bob", "IT")
            )
    );

    @GetMapping
    public List<Employee> getEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return "Created.";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); ++i) {
            if (employees.get(i).getId().equals(id)) {
                employees.set(i, updatedEmployee);
                break;
            }
        }
        return "Updated.";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employees.removeIf(e -> e.getId().equals(id));
        return "Deleted.";
    }
}
