//Communicates with the database
// Contains JPA interface
// Executes queries, It goes through JPA to the Hibernate


package com.bonginkosi.userservice.repository;

import com.bonginkosi.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //query to get user by name
    User findByName(String name);

    //query to get user by role
    List<User> findByRole(String role);

   //query to get user by email and mobile numbers(using request params)
    User findByEmailAndMobileNumbers(String email, String mobileNumbers);


    // query to return all users order by  name
    List<User> findAllByOrderByNameAsc();
}
