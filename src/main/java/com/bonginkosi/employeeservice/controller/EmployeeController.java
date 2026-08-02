// Handles HPP requests from the client(POST,GET,PUT,PATCH, and DELETE)
//Accepts data in a JSON format
//Returns JSON responses
// Communicates with the Service layer

package com.bonginkosi.employeeservice.controller;

import com.bonginkosi.employeeservice.dto.EmployeeDto;
import com.bonginkosi.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // POST method to create new employee
    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    //GET method to get employee by name
    @GetMapping("/name/{name}")
    public EmployeeDto getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }

    //GET method to get employee by role
    @GetMapping("/role/{role}")
    public List<EmployeeDto> getEmployeeByRole(@PathVariable String role) {
        return employeeService.getEmployeeByRole(role);
    }


    // Using request params(find by email and mobile num)
    //These are key value pairs or extra details that the client sends to the server along with the HTTP method to control or get the desired response
    @GetMapping("/search")
    public EmployeeDto getEmployeeByEmailAndMobileNumber(@RequestParam String email, @RequestParam String mobileNumbers) {

        return employeeService.getEmployeeByEmailAndMobileNumber(email, mobileNumbers);
    }

    //A patch method to update employee's email and mobileNumbers
    @PatchMapping("/{id}")
    public EmployeeDto updateEmailAndMobile(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {

        return employeeService.updateEmailAndMobile(id, employeeDto);
    }

    //Method to delete employee by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    //A GET method list to return all employees order by  name
    @GetMapping
    public List<EmployeeDto> getAllEmployeesOrderedByName() {
        return employeeService.getAllEmployeesOrderedByName();
    }

     // PUT method to fully update employee's information
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {

        return employeeService.updateEmployee(id, employeeDto);
    }

   //Method to get employee by id - OpenFeign calls this endpoint to verify if the employee exist before creating a salary record/
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

}
