package hw_2023_04_10.service;

import hw_2023_04_10.domain.Employee;
import hw_2023_04_10.exception.EmployeeAlreadyAddedException;
import hw_2023_04_10.exception.EmployeeNotFoundException;
import hw_2023_04_10.exception.EmployeeStorageIsFullException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 3;
    private final List<Employee> employees = new ArrayList<>(SIZE);

    @PostConstruct
    public void init() {
        employees.add(new Employee("Олег", "Олегов", 1, 10_000));
        employees.add(new Employee("Максим", "Максимов", 2, 20_000));
    }

    public Employee add(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.size() < SIZE) {
            for (Employee emp : employees) {
                if (emp.equals(employee)) {
                    throw new EmployeeAlreadyAddedException(employee);
                }
            }
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException(employee);
    }

    public Employee find(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException(employee);
    }

    public Employee remove(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.remove(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException(employee);
    }

    public List<Employee> list(){
        return Collections.unmodifiableList(employees);
    }
    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }
}


