package org.tyutyunik.employeeStream.controller;

import org.springframework.web.bind.annotation.*;
import org.tyutyunik.employeeStream.model.Employee;
import org.tyutyunik.employeeStream.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String standardAnswer() {
        return employeeService.standardAnswer();
    }

    @GetMapping("/get/all")
    public List<Employee> getData() {
        return employeeService.getData();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam int departmentId,
                        @RequestParam double salary) {
        return employeeService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/departments/max-salary")
    public Employee salaryMaxByDepartment(@RequestParam int departmentId) {
        return employeeService.salaryMaxByDepartment(departmentId);
    }

    @GetMapping("/departments/min-salary")
    public Employee salaryMinByDepartment(@RequestParam int departmentId) {
        return employeeService.salaryMinByDepartment(departmentId);
    }

    @GetMapping(path = "/departments/all", params = "departmentId")
    public List<Employee> employeesByDepartment(@RequestParam int departmentId) {
        return employeeService.employeesByDepartment(departmentId);
    }

    @GetMapping("/departments/all")
    public List<Employee> employeesDivideByDepartment() {
        return employeeService.employeesDivideByDepartment();
    }
}
