package UnitTestingCode.UtDemo.Controller;

import UnitTestingCode.UtDemo.Error.EmployeeNotFoundException;
import UnitTestingCode.UtDemo.Model.Employee;
import UnitTestingCode.UtDemo.Services.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/addemployee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employeeAdd)
    {
        try{
             Employee employee = employeeService.SaveEmployee(employeeAdd);
             logger.info("Inside create department of the employeeController");
            return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
            }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
       }

    @GetMapping("/getByIds/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeesById
            (@PathVariable("id") int eid) throws EmployeeNotFoundException
    {
        Optional<Employee> employee = employeeService.GetEmployeesById(eid);
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "Getting employee by id");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employee);
    }

    @GetMapping("/getById/{eid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("eid") int id)
    {
        try{
            Employee employee =  employeeService.GetEmployeeById(id);

            logger.info("Inside getEmployeeById of the employeeController");
            return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
           }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }

        @GetMapping(value = "/getByName/{name}")
        public ResponseEntity<Employee> getEmployeeByName(@RequestParam(value = "name") String name)
        {
            try {
                Employee employee = employeeService.getEmployeeByName(name);
            return new ResponseEntity<Employee>(employee,HttpStatus.FOUND);
             }
            catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @GetMapping("/GetAll")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        try{
            List<Employee> employees = employeeService.GetAllEmployee();
            return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/UpdateDataByID/{id}")
    public ResponseEntity<Employee> UpdateDataByID(@PathVariable(value = "id") int id, @RequestBody Employee employee)
    {
        try{
            Employee existing = employeeService.GetEmployeeById(id); //Mock1

            existing.setName(employee.getName());
            existing.setAddress(employee.getAddress());

            Employee updatedEmployee = employeeService.UpdateEmployeeById(id, existing); //Mock2
            return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK); //return output - controller statement

        }
        catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<Employee> DeleteEmployee(@PathVariable(value = "id") int id)
    {
        Employee employee = null;
        try{
            employee =  employeeService.GetEmployeeById(id);
            employeeService.DeleteEmployee(id);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
