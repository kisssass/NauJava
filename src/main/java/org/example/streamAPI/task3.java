package org.example.streamAPI;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class task3 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иванов Иван", 30, "IT", 60000.0));
        employees.add(new Employee("Петров Петр", 25, "HR", 45000.0));
        employees.add(new Employee("Сидоров Сидор", 35, "Finance", 70000.0));
        employees.add(new Employee("Алексеев Алексей", 28, "Marketing", 5000000.0));
        employees.add(new Employee("Николаев Николай", 40, "IT", 80000.0));

        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .toList();

        sortedEmployees.forEach(System.out::println);
    }
}
