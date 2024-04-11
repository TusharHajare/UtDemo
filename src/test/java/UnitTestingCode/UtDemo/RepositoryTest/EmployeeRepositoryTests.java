package UnitTestingCode.UtDemo.RepositoryTest;

//import UnitTestingCode.UtDemo.Model.Employee;
import UnitTestingCode.UtDemo.Model.Employee;
import UnitTestingCode.UtDemo.Repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTests
{
    @Autowired
    private EmployeeRepository employeeRepository;

//Another way of testing
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @BeforeEach
//    void setUp()
//    {
//        Employee employee = Employee
//                .builder()
//                .name("Vishal")
//                .address("Jamkhed")
//                .build();
//
//        testEntityManager.persist(employee);
//    }
//
//    @Test // --> Another way**  (Not run)
//    public void whenFindById_thenReturnEmployee()
//    {
//        Employee employee = employeeRepository.findById(1).get();
//
//        Assertions.assertThat(employee).isNotNull();
//        assertEquals(employee.getName(), "Vishal");
//        }

    @Test
    public void EmployeeRepository_SaveAll_ReturnedSavedEmployee()
    {
        //Arrange
        Employee employee = Employee.builder().employeeId(2)
                .name("Manish")
                .address("Karmala")
                .build();

        //Act
        Employee savedEmployee = employeeRepository.save(employee);

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
//        Assertions.assertThat(savedEmployee.getEmployeeId(), 2);
        Assertions.assertThat(savedEmployee.getEmployeeId()).isGreaterThan(0);
    }

    @Test
    public void EmployeeRepository_GetAll_ReturnAllEmployees()
    {
        //Arrange
        Employee employee = Employee.builder().name("Manoj").address("Latur").build();
        Employee employee1 = Employee.builder().name("Manisha").address("Beed").build();

        //Act
         employeeRepository.save(employee);
         employeeRepository.save(employee1);

        List<Employee> employeeList = employeeRepository.findAll();

        //Assert
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
     }

     @Test
     public void EmployeeRepository_GetById_ReturnEmployee()
     {
         //Action
         Employee employee = Employee.builder().name("Manshi").address("cilicon").build();

         employeeRepository.save(employee);
         //Act
         Employee employee1 = employeeRepository.findById(employee.getEmployeeId()).get();

         //Assert
         Assertions.assertThat(employee1).isNotNull();

     }

     @Test
    public void EmployeeRepository_GetByName_ReturnEmployeeNotNull()
    {
        //Action
        Employee employee = Employee.builder().name("Manshi").address("cilicon").build();

        employeeRepository.save(employee);

        //Act
        Employee employeeData = employeeRepository.findByName(employee.getName());

        //Assert
        Assertions.assertThat(employeeData).isNotNull();

    }

    @Test
    public void EmployeeRepository_UpdateEmployee_returnUpdatedEmployee()
    {
        Employee employee = Employee.builder().name("MOnika").address("Pune").build();
        employeeRepository.save(employee);
        Employee employee1 = employeeRepository.findById(employee.getEmployeeId()).get();
        employee1.setName("Monika Nale");
        employee1.setAddress("punha pune");
        Employee UpdatedEmployee = employeeRepository.save(employee1);

        Assertions.assertThat(UpdatedEmployee.getName()).isNotNull();
        Assertions.assertThat(UpdatedEmployee.getAddress()).isNotNull();
    }

    @Test
    public void EmployeeRepository_DeleteById_ReturnEmployeeIsEmpty()
    {
        //Action
        Employee employee = Employee.builder().name("Manshi").address("cilicon").build();

        employeeRepository.save(employee);

        //Act
         employeeRepository.deleteById(employee.getEmployeeId());
        Optional<Employee> ReturnEmployee = employeeRepository.findById(employee.getEmployeeId());
        //Assert
        Assertions.assertThat(ReturnEmployee).isEmpty();

    }
}
