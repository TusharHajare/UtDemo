package UnitTestingCode.UtDemo.ControllerTest;
import UnitTestingCode.UtDemo.Controller.EmployeeController;
import UnitTestingCode.UtDemo.Model.Employee;
import UnitTestingCode.UtDemo.Services.EmployeeService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeControllerTest.class})
//@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest
{
  @Mock
  EmployeeService employeeService;

  @InjectMocks
  EmployeeController employeeController;

  List<Employee>  employeeList;

  Employee employee;

  @Test
  @Order(1)
  public void test_getAllEmployees()
  {
    employeeList = new ArrayList<Employee>();
    employeeList.add(new Employee(1,"Tushar Hajare","Ahmednagar"));
    employeeList.add(new Employee(1,"nikita","panvel"));

    when(employeeService.GetAllEmployee()).thenReturn(employeeList);
    ResponseEntity<List<Employee>> response = employeeController.getAllEmployee();

    Assertions.assertEquals(HttpStatus.FOUND, response.getStatusCode());
    Assertions.assertEquals(2,response.getBody().size());
  }

  @Test
  @Order(2)
  public void test_getEmployeeById()
  {
    Employee employee1 = new Employee(2, "Manoj", "Latur");
    int empId = 2;
    when(employeeService.GetEmployeeById(empId)).thenReturn(employee1);
    ResponseEntity<Employee> res = employeeController.getEmployeeById(empId);

    Assertions.assertEquals(HttpStatus.FOUND, res.getStatusCode());
    Assertions.assertEquals(empId, res.getBody().getEmployeeId());
  }

  @Test
  @Order(3)
  public void test_getEmployeeByName()
  {
    Employee employee1 = new Employee(2, "Tushar", "Latur");
    String empName = "Tushar";

    when(employeeService.getEmployeeByName(empName)).thenReturn(employee1);
    ResponseEntity<Employee> res = employeeController.getEmployeeByName(empName);

    Assertions.assertEquals(HttpStatus.FOUND, res.getStatusCode());
    Assertions.assertEquals(empName, res.getBody().getName());
  }

  @Test
  @Order(4)
  public void test_addEMployee()
  {
    employee = new Employee(2, "Tushar", "Latur");

    when(employeeService.SaveEmployee(employee)).thenReturn(employee);
    ResponseEntity<Employee> res = employeeController.createEmployee(employee);

    Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
    Assertions.assertEquals(employee, res.getBody());

  }

  @Test
  @Order(5)
  public void test_UpdateEmployee()
  {
    Employee employee1 = new Employee(2, "Manisha", "Pune");
    int empId = 2;

    when(employeeService.GetEmployeeById(empId)).thenReturn(employee1);
    when(employeeService.UpdateEmployeeById(empId, employee1)).thenReturn(employee1);

    ResponseEntity<Employee> res = employeeController.UpdateDataByID(empId, employee1);

    Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    Assertions.assertEquals(empId, res.getBody().getEmployeeId());
    Assertions.assertEquals("Manisha", res.getBody().getName());
    Assertions.assertEquals("Pune", res.getBody().getAddress());
  }

    @Test
    @Order(6)
   public void test_deleteemployee()
    {
        Employee employee1 = new Employee(2, "Manisha", "Pune");
        int empId = 3;

        when(employeeService.GetEmployeeById(empId)).thenReturn(employee1);
        ResponseEntity<Employee> res = employeeController.DeleteEmployee(empId);

        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

    }

}
