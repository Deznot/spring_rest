package com.denisselitsky.spring.rest.controller;

import com.denisselitsky.spring.rest.entity.Employee;
import com.denisselitsky.spring.rest.exception_handling.NoSuchEmployeeException;
import com.denisselitsky.spring.rest.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    private final EmployeeService employeeService;

    public MyRESTController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployee() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee showEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if(employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }
}
