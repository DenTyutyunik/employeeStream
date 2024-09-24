package org.tyutyunik.employeeStream.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tyutyunik.employeeStream.model.Employee;

import java.util.List;

public interface EmployeeService {
    String standardAnswer();

    List<Employee> getData();

    Employee addEmployee(String firstName, String lastName, int departmentId, double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Employee salaryMaxByDepartment(int departmentId);

    Employee salaryMinByDepartment(int departmentId);

    List<Employee> employeesByDepartment(int departmentId);

    List<Employee> employeesDivideByDepartment();
}
