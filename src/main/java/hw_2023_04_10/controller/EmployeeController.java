package hw_2023_04_10.controller;

import hw_2023_04_10.domain.Employee;
import hw_2023_04_10.exception.EmployeeAlreadyAddedException;
import hw_2023_04_10.exception.EmployeeNotFoundException;
import hw_2023_04_10.exception.EmployeeStorageIsFullException;
import hw_2023_04_10.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam ("firstName") String firstName,
                        @RequestParam ("lastName")String lastName,
                        @RequestParam ("departmentId")int department,
                        @RequestParam ("salary") int salary) {
        return  employeeService.add(firstName, lastName, department, salary);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam ("firstName") String firstName,
                           @RequestParam ("lastName")String lastName,
                           @RequestParam ("departmentId")int department,
                           @RequestParam ("salary") int salary) {
        return  employeeService.remove(firstName, lastName, department, salary);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam ("firstName") String firstName,
                         @RequestParam ("lastName")String lastName,
                         @RequestParam ("departmentId")int department,
                         @RequestParam ("salary") int salary) {
        return  employeeService.find(firstName, lastName, department, salary);
    }

    @GetMapping
    public List<Employee> list() {
        return  employeeService.list();
    }
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public ResponseEntity<String> EmployeeAlreadyAddedExceptionHandler (EmployeeAlreadyAddedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Сотрудник " + e.getEmployee() + " уже есть в списке!");
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> EmployeeNotFoundExceptionHandler (EmployeeNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Сотрудник " + e.getEmployee() + " не найден!");
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public ResponseEntity<String> EmployeeStorageIsFullExceptionHandler (EmployeeStorageIsFullException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Список полон - сотрудника " + e.getEmployee() + " не добавить !");
    }

}
