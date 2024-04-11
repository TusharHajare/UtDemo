package UnitTestingCode.UtDemo.Repository;


import UnitTestingCode.UtDemo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
    Employee findByName(String name);
    Employee findByNameIgnoreCase(String name);
    // By using this method -> find the employee names in any form like uppercase and lowercase letters
}
