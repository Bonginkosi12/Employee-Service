// Handles HPP requests from the client(POST,GET,PUT,PATCH, and DELETE)
//Accepts data in a JSON format
//Returns JSON responses
// Communicates with the Service layer

package com.bonginkosi.userservice.controller;

import com.bonginkosi.userservice.dto.UserDto;
import com.bonginkosi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST method to create new users
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    //GET method to get user by name
    @GetMapping("/name/{name}")
    public UserDto getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    //GET method to get user by role
    @GetMapping("/role/{role}")
    public List<UserDto> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }


    // Use request params(find by email and mobile num)
    //These are key value pairs or extra details that the client sends to the server along with the HTTP method to control or get the desired response
    @GetMapping("/search")
    public UserDto getUserByEmailAndMobileNumber(@RequestParam String email, @RequestParam String mobileNumbers) {

        return userService.getUserByEmailAndMobileNumber(email, mobileNumbers);
    }

    //A patch method to update user's email and mobileNumbers
    @PatchMapping("/{id}")
    public UserDto updateEmailAndMobile(@PathVariable Integer id, @RequestBody UserDto userDto) {

        return userService.updateEmailAndMobile(id, userDto);
    }

    //Method to delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    //A GET method list to return all users order by  name
    @GetMapping
    public List<UserDto> getAllUsersOrderedByName() {
        return userService.getAllUsersOrderedByName();
    }

     // PUT method to fully update user's information
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {

        return userService.updateUser(id, userDto);
    }

}
