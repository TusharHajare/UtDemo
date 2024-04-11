package UnitTestingCode.UtDemo.ServicesTest;

import UnitTestingCode.UtDemo.Model.Employee;
import UnitTestingCode.UtDemo.Repository.EmployeeRepository;
import UnitTestingCode.UtDemo.Services.EmployeeService;
import org.assertj.core.api.Assertions;
import org.h2.command.dml.MergeUsing;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.Timeout;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest
{
    @MockBean
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

//    @BeforeEach  --> Another way**
//        // This will executed before each and every test method calling
//        //@BeforeAll ->  This will executed before all test method calling
//        // @AfterEach -> This will deleted the data after each and every test method calling
//        // @AfterAll  -> This will deleted all the data after all test method calling
//    void setUp()
//    {
//        Employee employee = Employee
//                .builder()
//                .name("Vishal")
//                .address("Jamkhed")
//                .build();
//
//        Mockito.when(employeeRepository.findByName("Vishal")).thenReturn(employee);
//    }

//    @Test --> Another way**  (Not run)
//    public void whenGetEmployeeByName_thenReturnEmployee()
//    {
//        String named = "kzkbjk";
//
//        Employee found = employeeService.getEmployeeByName(named);
//
//        assertEquals(named, found.getName());
//}

    @Test
    public void EmployeeService_CreateEmployee_returnEmployee() {
        //Arrange
        Employee employees = Employee.builder().name("Tushar").address("Ahmednagar").build();

        //Act
        when(employeeRepository.save(employees)).thenReturn(employees);
        Employee savedPokemon = employeeService.SaveEmployee(employees);

        //Assert
        Assertions.assertThat(savedPokemon).isNotNull();

        //If u are using Dto class then below code
        //Arrange
//        EmployeeDto employeeDto = EmployeeDto.builder().name("man").address("mahakal").build();
        //Act
//        when(employeeRepository.save(Mockito.any(EmployeeDto.class))).thenReturn(employeeDto);
//        EmployeeDto savedPokemon = employeeService.SaveEmployee(employeeDto);
        //Assert
//        Assertions.assertThat(savedPokemon).isNotNull();
    }


    @Test
    public void EmployeeService_FindById_ReturnEmployee()
    {

        //Arrange
        List<Employee> employees = new ArrayList<Employee>();

        employees.add(new Employee(1,"man", "mahakal"));
        employees.add(new Employee(2,"Homosapien", "forest"));
        int empId=1;
        //Act
        when(employeeRepository.findAll()).thenReturn(employees);
        Employee getemployee = employeeService.GetEmployeeById(empId);

        //Assert
        Assertions.assertThat(getemployee).isNotNull();
        assertEquals(empId, getemployee.getEmployeeId());
    }

    @Test
    public void EmployeeService_FindByName_ReturnEmployee()
    {

        //Arrange
        List<Employee> employees = new ArrayList<Employee>();

        employees.add(new Employee(1,"man", "mahakal"));
        employees.add(new Employee(2,"Homosapien", "forest"));
        String empName="Homosapien";

        //Act
        when(employeeRepository.findAll()).thenReturn(employees);
        Employee getemployee = employeeService.getEmployeeByName(empName);

        //Assert
        Assertions.assertThat(getemployee).isNotNull();
        assertEquals(empName, getemployee.getName());
    }

    @Test
    public void test_updateemployeeById_returnEmployee()
    {
        Employee employee = new Employee(1,"man", "mahakal");
        int empid=1;

        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee saveEmployee = employeeService.UpdateEmployeeById(empid, employee);

        assertEquals(empid, saveEmployee.getEmployeeId());
        Assertions.assertThat(saveEmployee.getName()).isNotNull();
        Assertions.assertThat(saveEmployee.getAddress()).isNotNull();
        Assertions.assertThat(saveEmployee).isNotNull();

    }

//    @Test --> This is also run
//    public void EmployeeService_UpdateEmployee_returnUpdatedEmployee()
//    {
//        Employee employees = Employee.builder().name("MOnika").address("Pune").build();
//
//        Mockito.lenient().when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employees));
//        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employees);
//
//        Employee saveEmployee = employeeService.UpdateEmployeeById(1, employees);
//
//        Assertions.assertThat(saveEmployee.getName()).isNotNull();
//        Assertions.assertThat(saveEmployee.getAddress()).isNotNull();
//        Assertions.assertThat(saveEmployee).isNotNull();
//    }

    @Test
    public void EmployeeService_DeleteById_ReturnEmployee()
    {
        //Arrange
        Employee employees = Employee.builder().name("Man").address("panvel").build();

        //Act
        //Unnecessary stubbings detected- Thats why using the Mockito.lenient()
       Mockito.lenient().when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employees));
        //Assert
       assertAll(() -> employeeService.DeleteEmployee(1));
    }

    //Another way of deleted employee by id if return statement is not available
    @Test
    public void test_deleteEmployeeById()
    {
        Employee employee = new Employee(1,"man", "mahakal");
        int empid=1;

        employeeService.DeleteEmployee(empid);
        verify(employeeRepository, times(1)).deleteById(empid);
    }

    @Test
    public void test_findallemployees()
    {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(1,"Prakash", "Pune"));
        employeeList.add(new Employee(2,"Prashant", "Kolkata"));

        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employees = employeeService.GetAllEmployee();

        assertEquals(2, employees.size());
    }



}
