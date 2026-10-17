package com.example.spring.chapter29;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
We have a list of Employee objects. Each employee has an id, name, salary (double)
and department (String). Write a Java 8 Stream pipeline to find all employees in the "IT"
department who earn less than $50,000 give them a 10% raise, and collect the updated
list of only those employees
 */
class EmployeeSalary {
    private int id;
    private String name;
    private double salary;
    private String department;

    // Constructors, getters, setters, and toString()
    public EmployeeSalary(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public int getId() {
        return id;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", name: " + name + ", department: " + department + ", and salary: " + salary;
    }
}

public class StreamEmployeeTransformation {
    public static void main(String[] args) {
        List<EmployeeSalary> employees = Arrays.asList(
                new EmployeeSalary(1, "Alice", 45000, "IT"),
                new EmployeeSalary(2, "Bob", 60000, "HR"),
                new EmployeeSalary(3, "Charlie", 40000, "HR"),
                new EmployeeSalary(4, "David", 30000, "IT")
        );

        List<EmployeeSalary> hikedEmployees = employees.stream()
                // Filter out the matching criteria
                .filter(employee -> "IT".equalsIgnoreCase(employee.getDepartment()))
                .filter(employee -> employee.getSalary() < 50000)
                .map(emp -> {
                    emp.setSalary(emp.getSalary() * 1.1);
                    return emp;
                }).toList();
        System.out.println(hikedEmployees);
        Map<String, Set<String>> namesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(EmployeeSalary::getDepartment,
                        Collectors.mapping(EmployeeSalary::getName, Collectors.toSet())));
        System.out.println(namesByDepartment);
    }
}
