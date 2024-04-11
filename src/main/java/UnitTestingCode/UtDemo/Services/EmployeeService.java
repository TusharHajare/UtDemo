package UnitTestingCode.UtDemo.Services;

import UnitTestingCode.UtDemo.Error.EmployeeNotFoundException;
import UnitTestingCode.UtDemo.Model.Employee;
import UnitTestingCode.UtDemo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeInterface
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee SaveEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee GetEmployeeById(int id) // --> This is also run the method but for exception taking next one
    {
      List<Employee> employeeList = employeeRepository.findAll();
      Employee employee = null;

      for(Employee emp: employeeList)
      {
          if(emp.getEmployeeId()==id)
              employee= emp;
      }
      return employee;

    }

    public Optional<Employee> GetEmployeesById(int id) throws EmployeeNotFoundException
    {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(!employee.isPresent())
        {
            throw new EmployeeNotFoundException("Employee Not found!");
        }
        return employee;
    }

    public Employee getEmployeeByName(String name)
    {
        List<Employee> employees = employeeRepository.findAll();
        Employee employee = null;

        for(Employee emp: employees)
        {
           if(emp.getName().equalsIgnoreCase(name))
           {
               employee = emp;
           }
        }
        return employee;
    }

    public List<Employee> GetAllEmployee()
    {
        List<Employee> employeeList =  employeeRepository.findAll();
        return employeeList;
    }

    public void DeleteEmployee(int id)
    {
        employeeRepository.deleteById(id);
    }

    public Employee UpdateEmployeeById(int id, Employee employee)
    {
         employee.setEmployeeId(id);
        return employeeRepository.save(employee);
    }


}
