package UnitTestingCode.UtDemo.Services;

import UnitTestingCode.UtDemo.Error.EmployeeNotFoundException;
import UnitTestingCode.UtDemo.Model.Employee;

import java.util.List;

public interface EmployeeInterface
{
    public Employee SaveEmployee(Employee employee);

    public Employee GetEmployeeById(int id) throws EmployeeNotFoundException;

    public Employee getEmployeeByName(String name);

    public List<Employee> GetAllEmployee();

    public void DeleteEmployee(int id);

    public Employee UpdateEmployeeById(int id, Employee employee);

}
