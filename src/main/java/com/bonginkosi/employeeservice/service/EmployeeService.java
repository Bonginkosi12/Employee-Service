// Contains business logic
// Decides what should happen
// Coordinates data from repositories
// Communicates with the Repository layer

package com.bonginkosi.employeeservice.service;

import com.bonginkosi.employeeservice.dto.EmployeeDto;
import com.bonginkosi.employeeservice.entity.Employee;
import com.bonginkosi.employeeservice.event.EmployeeCreatedEvent;
import com.bonginkosi.employeeservice.producer.EmployeeEventProducer;
import com.bonginkosi.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    private final EmployeeEventProducer eventProducer;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeEventProducer eventProducer) {
        this.employeeRepository = employeeRepository;
        this.eventProducer = eventProducer;
    }

    // Method to create a new employee(resource)
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobileNumbers(employeeDto.getMobileNumbers());
        employee.setRole(employeeDto.getRole());
        employee.setEmploymentType(employeeDto.getEmploymentType());
        employee.setDepartment(employeeDto.getDepartment());
        employeeRepository.save(employee);

        //Event to communicate with other services
        EmployeeCreatedEvent event = new EmployeeCreatedEvent(
                employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        );

        //Publish event to RabbitMQ
        eventProducer.publishEmployeeCreated(event);

        return employeeDto;
    }

    // Method to get employee by name
    public EmployeeDto getEmployeeByName(String name) {
        Employee employee = employeeRepository.findByName(name);

        if (employee == null) {
            return null;
        }

        return new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        );
    }

    // Method to get employee by role
    public List<EmployeeDto> getEmployeeByRole(String role) {
        List<Employee> employees = employeeRepository.findByRole(role);

        return employees.stream().map(employee -> new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        )).toList();
    }


    // Method using request params to find employee by email and mobileNumbers
    public EmployeeDto getEmployeeByEmailAndMobileNumber(String email, String mobileNumbers) {
        Employee employee = employeeRepository.findByEmailAndMobileNumbers(email, mobileNumbers);

        if (employee == null) {
            return null;
        }

        return new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        );
    }

    //A patch method to update employee's email and mobileNumbers
    public EmployeeDto updateEmailAndMobile(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (employeeDto.getEmail() != null) {
            employee.setEmail(employeeDto.getEmail());
        }

        if (employeeDto.getMobileNumbers() != null) {
            employee.setMobileNumbers(employeeDto.getMobileNumbers());
        }

        employeeRepository.save(employee);

        return new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        );
    }

    // Method to delete employee
    public void deleteEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        employeeRepository.delete(employee);
    }

    //A GET method list to return all employees order by  name
    public List<EmployeeDto> getAllEmployeesOrderedByName() {

        List<Employee> employees = employeeRepository.findAllByOrderByNameAsc();

        return employees.stream().map(employee -> new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        )).toList();
    }

    //A PUT method to fully update the employee
    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));


        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setEmail(employeeDto.getEmail());
        employee.setMobileNumbers(employeeDto.getMobileNumbers());
        employee.setRole(employeeDto.getRole());
        employee.setEmploymentType(employeeDto.getEmploymentType());
        employee.setDepartment(employeeDto.getDepartment());
        employeeRepository.save(employee);

        return new EmployeeDto(
                employee.getName(),
                employee.getSurname(),
                employee.getAge(),
                employee.getEmail(),
                employee.getMobileNumbers(),
                employee.getRole(),
                employee.getEmploymentType(),
                employee.getDepartment()
        );
    }

    //Method to fetch the employee from the database and convert it to a DTO
    public EmployeeDto getEmployeeById(Integer id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EmployeeDto dto = new EmployeeDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSurname(employee.getSurname());
        dto.setAge(employee.getAge());
        dto.setEmail(employee.getEmail());
        dto.setMobileNumbers(employee.getMobileNumbers());
        dto.setRole(employee.getRole());
        dto.setEmploymentType(employee.getEmploymentType());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }
}
