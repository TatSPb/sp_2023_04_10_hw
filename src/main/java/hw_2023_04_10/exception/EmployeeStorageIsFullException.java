package hw_2023_04_10.exception;

import hw_2023_04_10.domain.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class EmployeeStorageIsFullException extends RuntimeException{
    private final Employee employee;

    public EmployeeStorageIsFullException(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee(){
        return employee;
    }
}
