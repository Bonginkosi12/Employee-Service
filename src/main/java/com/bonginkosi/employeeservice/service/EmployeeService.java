// Contains business logic
// Decides what should happen
// Coordinates data from repositories
// Communicates with the Repository layer

package com.bonginkosi.employeeservice.service;

import com.bonginkosi.employeeservice.dto.EmployeeDto;
import com.bonginkosi.employeeservice.entity.Employee;
import com.bonginkosi.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    // Method to create a new user(resource)
    public EmployeeDto createUser(EmployeeDto userDto) {
        Employee employee = new Employee();
        employee.setName(userDto.getName());
        employee.setSurname(userDto.getSurname());
        employee.setAge(userDto.getAge());
        employee.setEmail(userDto.getEmail());
        employee.setMobileNumbers(userDto.getMobileNumbers());
        employee.setRole(userDto.getRole());
        employee.setEmploymentType(userDto.getEmploymentType());
        employee.setDepartment(userDto.getDepartment());
        employeeRepository.save(employee);

        return userDto;
    }

    // Method to get user by name
    public EmployeeDto getUserByName(String name) {
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

    // Method to get user byle
    public List<EmployeeDto> getUsersByRole(String role) {
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


    // Method using request params to find user by email and mobileNumbers
    public EmployeeDto getUserByEmailAndMobileNumber(String email, String mobileNumbers) {
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

    //A patch method to update user's email and mobileNumbers
    public EmployeeDto updateEmailAndMobile(Integer id, EmployeeDto userDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDto.getEmail() != null) {
            employee.setEmail(userDto.getEmail());
        }

        if (userDto.getMobileNumbers() != null) {
            employee.setMobileNumbers(userDto.getMobileNumbers());
        }

        employeeRepository.save(employee);

        return new EmployeeDto(
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
    }

    // Method to delete user
    public void deleteUserById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        employeeRepository.delete(employee);
    }

    //A GET method list to return all users order by  name
    public List<EmployeeDto> getAllUsersOrderedByName() {

        List<Employee> employees = employeeRepository.findAllByOrderByNameAsc();

        return employees.stream().map(employee -> new EmployeeDto(
                employee.getId(),
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

    //A PUT method to fully update the user
    public EmployeeDto updateUser(Integer id, EmployeeDto userDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        employee.setName(userDto.getName());
        employee.setSurname(userDto.getSurname());
        employee.setAge(userDto.getAge());
        employee.setEmail(userDto.getEmail());
        employee.setMobileNumbers(userDto.getMobileNumbers());
        employee.setRole(userDto.getRole());
        employee.setEmploymentType(userDto.getEmploymentType());
        employee.setDepartment(userDto.getDepartment());
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

    //Method to fetch the user from the database and convert it to a DTO
    public EmployeeDto getUserById(Integer id) {

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
