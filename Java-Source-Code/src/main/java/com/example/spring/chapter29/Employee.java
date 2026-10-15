package com.example.spring.chapter29;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private int id;
    private String department;
    private double salary;

    Employee() {

    }

    Employee(String name, int id, String department, double salary) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return "Name: " + this.name + ", id: " + this.id + ", department: " + this.department
                 + ", salary: $" + this.salary;
    }

    public static void main(String[] args) {
        // create five employees
        Employee rakesh = new Employee("Rakesh", 1234, "HR", 10000);
        Employee kamal = new Employee("Kamal", 1456, "IT", 20000);
        Employee shyam = new Employee("Shyam", 1389, "Sales", 30000);
        Employee ram = new Employee("Ram", 2343, "HR", 10000);
        Employee tanvi = new Employee("Tanvi", 9893, "IT", 20000);

        List<Employee> employeeList = List.of(rakesh, kamal, shyam, ram, tanvi);
        employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(System.out::println);

        String input = "altimetrikabdff";

        Map<Character, Long> freq = input.chars()
                                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(freq);
    }
}
