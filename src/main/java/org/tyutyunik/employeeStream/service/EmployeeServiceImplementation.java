package org.tyutyunik.employeeStream.service;

import org.springframework.stereotype.Service;
import org.tyutyunik.employeeStream.model.Employee;
import org.tyutyunik.employeeStream.exceptions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private static final int EMPLOYEES_MAX = 10;
    private List<Employee> employees = new ArrayList<>();

    @Override
    public String standardAnswer() {
        return "Добро пожаловать в менеджер сотрудников";
    }

    @Override
    public List<Employee> getData() {
        System.out.println("getData");
        return employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) {
        if (employees.size() >= EMPLOYEES_MAX) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        boolean removed = employees.remove(employee);
        if (!removed) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee salaryMaxByDepartment(int departmentId) {
        return employees.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee salaryMinByDepartment(int departmentId) {
        return employees.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> employeesByDepartment(int departmentId) {
        return employees.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Employee> employeesDivideByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId))
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
    }
}
