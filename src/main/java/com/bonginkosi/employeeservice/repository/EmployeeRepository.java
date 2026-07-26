//Communicates with the database
// Contains JPA interface
// Executes queries, It goes through JPA to the Hibernate


package com.bonginkosi.employeeservice.repository;

import com.bonginkosi.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //query to get user by name
    Employee findByName(String name);

    //query to get user by role
    List<Employee> findByRole(String role);

   //query to get user by email and mobile numbers(using request params)
    Employee findByEmailAndMobileNumbers(String email, String mobileNumbers);


    // query to return all users order by  name
    List<Employee> findAllByOrderByNameAsc();
}
