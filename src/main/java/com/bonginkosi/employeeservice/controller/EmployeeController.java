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

    // POST method to create new users
    @PostMapping
    public EmployeeDto createUser(@RequestBody EmployeeDto userDto) {
        return employeeService.createUser(userDto);
    }

    //GET method to get user by name
    @GetMapping("/name/{name}")
    public EmployeeDto getUserByName(@PathVariable String name) {
        return employeeService.getUserByName(name);
    }

    //GET method to get user by role
    @GetMapping("/role/{role}")
    public List<EmployeeDto> getUsersByRole(@PathVariable String role) {
        return employeeService.getUsersByRole(role);
    }


    // Use request params(find by email and mobile num)
    //These are key value pairs or extra details that the client sends to the server along with the HTTP method to control or get the desired response
    @GetMapping("/search")
    public EmployeeDto getUserByEmailAndMobileNumber(@RequestParam String email, @RequestParam String mobileNumbers) {

        return employeeService.getUserByEmailAndMobileNumber(email, mobileNumbers);
    }

    //A patch method to update user's email and mobileNumbers
    @PatchMapping("/{id}")
    public EmployeeDto updateEmailAndMobile(@PathVariable Integer id, @RequestBody EmployeeDto userDto) {

        return employeeService.updateEmailAndMobile(id, userDto);
    }

    //Method to delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        employeeService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    //A GET method list to return all users order by  name
    @GetMapping
    public List<EmployeeDto> getAllUsersOrderedByName() {
        return employeeService.getAllUsersOrderedByName();
    }

     // PUT method to fully update user's information
    @PutMapping("/{id}")
    public EmployeeDto updateUser(@PathVariable Integer id, @RequestBody EmployeeDto userDto) {

        return employeeService.updateUser(id, userDto);
    }

   //Method to get user by id - OpenFeign calls this endpoint to verify if the user(employee)exist before creating a salary record/
    @GetMapping("/{id}")
    public EmployeeDto getUserById(@PathVariable Integer id){
        return employeeService.getUserById(id);
    }

}
